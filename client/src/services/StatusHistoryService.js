import axios from "axios";

const API_BASE_URL = import.meta.env.VITE_BASE_URI_STATUS_HISTORY;

const StatusHistoryService = {
    
    addNewStatusHistory(id) {
        return axios.post(API_BASE_URL, id);
    },

    getAllStatusHistory: async () => {
        return axios.get(API_BASE_URL);
    },

    getStatusHistoryById(id) {
        return axios.get(`${API_BASE_URL}/${id}`);
    },

    updateStatusHistory(currentStatusHistory, id) {
        return axios.put(`${API_BASE_URL}/${id}`, currentStatusHistory);
    },
    
    deleteStatusHistory(id) {
        return axios.delete(`${API_BASE_URL}/${id}`);
    },
};
export default StatusHistoryService;


