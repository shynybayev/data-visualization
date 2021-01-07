
export const sanitizeCountryNamesForCOVIDStats = countryName => {


	return countryName;
};

export const sanitizeCountryNamesForFlightInfo = countryName => {
	switch (countryName) {
		case "United States of America":
			countryName = "United States";
			break;
		default:
			break;
	}

	return countryName;
};
