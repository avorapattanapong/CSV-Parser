import axios from "axios";

const baseUrl = "http://localhost:8081/logs";
export const uploadCSV = (file) => {
  const formData = new FormData();
  formData.append("file", file);

  return axios.post(baseUrl, formData, {
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  });
}

export const fetchLogs = () => {
  return axios.get(baseUrl);
}

export const clearLogs = () => {
  return axios.delete(baseUrl);
}
