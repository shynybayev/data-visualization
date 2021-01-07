/** @author: kbtu data viz */

import React, { useState } from "react";
import "typeface-roboto";

import {
	createMuiTheme,
	makeStyles,
	ThemeProvider
} from "@material-ui/core/styles";

import { grey } from "@material-ui/core/colors";
import {
	AppBar,
	Button,
	Box,
	Hidden,
	IconButton,
	Toolbar,
	Typography,
	Grid
} from "@material-ui/core";

import FileCopyIcon from "@material-ui/icons/FileCopy";

import FavoriteIcon from "@material-ui/icons/Favorite";

import QRCode from "qrcode.react";

import CustomDrawer from "./components/Drawer";
import CustomModal from "./components/Modal";
import Home from "./pages/home";

const theme = createMuiTheme({
	palette: {
		primary: {
			main: grey[900]
		},
		secondary: {
			main: grey[50]
		}
	}
});

const useStyles = makeStyles(theme => ({
	addressText: {
		textAlign: "center",
		marginTop: 15,
		[theme.breakpoints.down("md")]: {
			flexWrap: "wrap",
			fontSize: 10,
			marginTop: 10
		}
	},
	menuButton: {
		marginRight: theme.spacing(2)
	},
	title: {
		flexGrow: 1
	}
}));


const App = () => {
	const classes = useStyles();

	const [drawerState, setDrawerState] = useState(false);

	const [modal, setModal] = useState(false);
	const [modalContent, setModalContent] = useState(null);

	const [copied, setCopied] = useState(false);

	const copyToClipboard = value => {
		navigator.clipboard.writeText(value);
		setCopied(true);
	};

	const sendViaQR = type => {
		let address = "";

		setModalContent(

		);
		handleModal(true);
	};

	const handleModal = value => {
		setModal(value);
	};

	const toggleDrawer = value => event => {
		if (
			event.type === "keydown" &&
			(event.key === "Tab" || event.key === "Shift")
		) {
			return;
		}
		setDrawerState(value);
	};

	return (
		<ThemeProvider theme={theme}>
			<AppBar position='static'>
				<Toolbar>
					<Typography variant='h6' className={classes.title}>
						Карта ковид по перелету
					</Typography>
					<Button
						color='inherit'
						startIcon={<FavoriteIcon />}
						onClick={toggleDrawer(true)}>
						<Hidden only={["sm", "xs"]}>Kbtu data visualization project</Hidden>
					</Button>
				</Toolbar>
			</AppBar>

			<CustomDrawer
				drawerState={drawerState}
				sendViaQR={sendViaQR}
				toggleDrawer={toggleDrawer}
			/>
			<Home />
			<CustomModal
				handleModal={handleModal}
				modal={modal}
				modalContent={modalContent}
			/>
		</ThemeProvider>
	);
};

export default App;
