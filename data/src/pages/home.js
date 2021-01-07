
import React, { useState, useEffect } from "react";
import axios from "axios";

import {
	CircularProgress,
	makeStyles,
	Typography,
	Grid,
	DialogContent,
	DialogContentText,
} from "@material-ui/core";

import Chart from "../components/Chart";
import GlobeChart from "../components/MapChart";

import { getData } from "../lib/httpClient";
import CustomTab from "../components/Tab";
import CustomDialog from "../components/Dialog";
import {
	sanitizeCountryNamesForFlightInfo,
	sanitizeCountryNamesForCOVIDStats,
} from "../lib/utils";

const url = "localhost:3000";
const pomberUrl = "https://pomber.github.io/covid19/timeseries.json";

console.log(url);
console.log(pomberUrl);

const useStyles = makeStyles((theme) => ({

	chart: {
		flexGrow: 1,
		height: 400,
	},
	flightInfo: {
		flexGrow: 1,
		height: 100,
	},
	root: {
		flexGrow: 1,
	},
	progress: {
		alignSelf: "center",
		justifySelf: "center",
		margin: theme.spacing(2),
	},
	modal: {
		top: "50%",
		left: "50%",
		transform: "translate(-50%, -50%)",
		position: "absolute",
		height: 500,
		width: 800,
		backgroundColor: theme.palette.background.paper,
		border: "2px solid #FFF",
		borderRadius: 10,
		boxShadow: theme.shadows[5],
		padding: theme.spacing(2, 4, 3),
		outline: "none",
	},
	mobileModal: {
		top: "25%",
		left: "25%",
		transform: "translate(-25%, -25%)",
		height: 295,
		width: 292,
	},
}));

const Home = () => {
	const classes = useStyles();

	const [loading, setLoading] = useState(false);

	const [bannedCountries, setBannedCountries] = useState("United States of America");
	const [countryData, setCountryData] = useState({});

	const [chartData, setChartData] = useState([]);
	const [dialog, setDialog] = useState(false);
	const [dialogContent, setDialogContent] = useState({});

	useEffect(() => {
		const cancel = axios.CancelToken.source();

		const fetchData = async () => {
			setLoading(true);

			let results = await getData(url, cancel);
			setBannedCountries(results);

			results = await getData(pomberUrl, cancel);
			setCountryData(results);

			setLoading(false);
		};

		fetchData();

		return () => {
			cancel.cancel();
		};
	}, []);

	const handleDialog = (value) => {
		setDialog(value);
	};

	const renderChart = (name) => {
		if (countryData[sanitizeCountryNamesForCOVIDStats(name)] !== undefined) {
			const getData = (type) => {
				return countryData[sanitizeCountryNamesForCOVIDStats(name)].map(
					(stat, index) => {
						return {
							x: `Day ${index}`,
							y: stat[type],
						};
					}
				);
			};

			setChartData([
				{
					id: "Подтверженных случаев",
					color: "hsl(40, 50%, 45%)",
					data: getData("confirmed"),
				},
				{
					id: "Умершие",
					color: "hsl(0, 50%, 45%)",
					data: getData("deaths"),
				},
				{
					id: "Вылечившие",
					color: "hsl(83, 70%, 50%)",
					data: getData("recovered"),
				},

			]);
			setDialogContent(getDialogContent(name, chartData));
			handleDialog(true);
		}
	};

	const getDialogContent = (name, chartData) => {
		const tabTitles = ["Flight Info", "Basic Stats"];
		const tabContents = [];

		tabContents.push(
			<Grid className={classes.flightInfo}>
				<Typography variant='h3'>{name}</Typography>
				<DialogContent dividers>
					<DialogContentText id='scroll-dialog-description' tabIndex={-1}>
						<Typography variant='body2'>
							{bannedCountries[sanitizeCountryNamesForFlightInfo(name)] ===
							undefined
								? "Нет информации о полете"
								: bannedCountries[sanitizeCountryNamesForFlightInfo(name)]}
						</Typography>
					</DialogContentText>
				</DialogContent>
			</Grid>
		);

		tabContents.push(
			<Grid className={classes.chart}>
				<Typography variant='h3'>{name}</Typography>
				<Chart data={chartData} />
			</Grid>
		);

		return (
			<Grid>
				<CustomTab tabContents={tabContents} tabTitles={tabTitles} />
			</Grid>
		);
	};

	if (loading)
		return (
			<div>
				<CircularProgress className={classes.progress} />
			</div>
		);

	return (
		<div className={classes.root}>
			<div>
				<GlobeChart countryData={countryData} renderChart={renderChart} />
				<CustomDialog
					handleDialog={handleDialog}
					dialog={dialog}
					dialogContent={dialogContent}
				/>
			</div>
		</div>
	);
};

export default Home;
