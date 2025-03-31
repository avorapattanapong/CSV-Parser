import {clearLogs, fetchLogs, uploadCSV} from "@/redux/api/logs";
import {
  clearingLogs,
  clearingLogsError,
  clearingLogsSuccess,
  getLogs,
  getLogsError,
  getLogsSuccess,
  uploadingCSV,
  uploadingCSVError,
  uploadingCSVSuccess
} from "@/redux/reducer/logs";

export const doGetLogs = () => (dispatch) => {
  dispatch(getLogs());

  return fetchLogs()
    .then((response) => {
      dispatch(getLogsSuccess(response.data));
      return Promise.resolve(response.data);
    })
    .catch((error) => {
      dispatch(getLogsError(error));
      return Promise.reject(error);
    });
}

export const doUploadCSV = (file) => (dispatch) => {
  dispatch(uploadingCSV());

  return uploadCSV(file)
    .then((response) => {
      dispatch(uploadingCSVSuccess(response.data));
      return Promise.resolve(response.data);
    })
    .catch((error) => {
      dispatch(uploadingCSVError(error.message));
      return Promise.reject(error);
    });
}

export const doClearLogs = () => (dispatch) => {
  dispatch(clearingLogs());

  return clearLogs()
    .then((response) => {
      dispatch(clearingLogsSuccess());
      return Promise.resolve(response.data);
    })
    .catch((error) => {
      dispatch(clearingLogsError(error.message));
      return Promise.reject(error);
    });
}
