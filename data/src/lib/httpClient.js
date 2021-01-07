
import axios from "axios";

export const getData = async (url, cancel) => {
	try {
		const results = await axios(url, { cancelToken: cancel.token });
		return results.data;
	} catch (error) {
		if (axios.isCancel(error)) {
			console.log("Request canceled", error.message);
		} else {
			console.log("Something went wrong: ", error.message);
		}
	}
};
