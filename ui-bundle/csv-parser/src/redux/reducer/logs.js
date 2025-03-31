import { createSlice } from "@reduxjs/toolkit";

export const logsSlice = createSlice({
  name: 'logs',
  initialState: {
    logs: [],
    currentUploadingFile: null,
    loading: false,
    error: null
  },
  reducers: {
    getLogs: state => {
      state.error = null;
      state.loading = true
    },
    getLogsSuccess: (state, action) => {
      state.logs = action.payload.data;
      state.loading = false;
    },
    getLogsError: (state, action) => {
      state.error = action.payload
      state.loading = false
    },
    uploadingCSV: state => {
      state.error = null;
      state.loading = true
    },
    uploadingCSVSuccess: (state, action) => {
      state.loading = false
    },
    uploadingCSVError: (state, action) => {
      state.error = action.payload
      state.loading = false
    },
    clearingLogs: state => {
      state.error = null;
      state.loading = true
    },
    clearingLogsSuccess: (state, action) => {
      state.logs = [];
      state.loading = false
    },
    clearingLogsError: (state, action) => {
      state.error = action.payload
      state.loading = false
    }
  }
});

export const {
    getLogs,
    getLogsSuccess,
    getLogsError,
    uploadingCSV,
    uploadingCSVSuccess,
    uploadingCSVError,
    clearingLogs,
    clearingLogsSuccess,
    clearingLogsError
} = logsSlice.actions;
export default logsSlice.reducer;
