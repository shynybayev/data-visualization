
import React, { memo, useEffect, useRef, useState } from "react";
import Globe from "react-globe.gl";
import { sanitizeCountryNamesForCOVIDStats } from "../lib/utils";

const MapChart = ({ countryData, renderChart }) => {
	const globeEl = useRef();
	const [hoverD, setHoverD] = useState();
	const [countries, setCountries] = useState({ features: [] });

	useEffect(() => {
		fetch("./countries.json")
			.then((res) => res.json())
			.then(setCountries);
	}, []);

	const todaysData = (countryName) => {
		countryName = sanitizeCountryNamesForCOVIDStats(countryName);

		if (countryData[countryName] !== undefined) {
			return countryData[countryName][countryData[countryName].length - 1];
		} else {
			return "No info";
		}
	};

	const getPolygonLabel = (data) => {
		if (todaysData(data.ADMIN) === "No info") return "No Info";
		else
			return `
        <b>${data.ADMIN}</b> <br />
        <strong>Случаи заболевания: </strong> <i>${
					todaysData(data.ADMIN).confirmed
				}</i><br/>
		<strong> Летальные исходы: </strong> <i>${todaysData(data.ADMIN).deaths}</i><br/>
		<strong>Выздоровело: </strong> <i>${todaysData(data.ADMIN).recovered}</i><br/>
      `;
	};

	return (
		<Globe
			ref={globeEl}
			globeImageUrl={require("../assets/earth-night.jpg")}
			backgroundImageUrl={require("../assets/night-sky-2.png")}
			polygonsData={countries.features}
			polygonAltitude={(d) => (d === hoverD ? 0.12 : 0.06)}
			polygonCapColor={(d) => (d === hoverD ? "green" : "blue")}
			polygonSideColor={() => "rgba(0, 100, 0, 0.15)"}
			polygonStrokeColor={() => "#111"}
			polygonLabel={({ properties: d }) => getPolygonLabel(d)}
			onPolygonHover={setHoverD}
			onPolygonClick={({ properties: d }) => renderChart(d.ADMIN)}
			polygonsTransitionDuration={300}
		/>
	);
};

export default memo(MapChart);
