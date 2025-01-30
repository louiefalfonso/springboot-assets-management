import axios from "axios";

const API_BASE_URL = import.meta.env.VITE_BASE_URI_STATUS_TRACKING;

const StatusTrackingService = {
    
    addNewStatusTracking(id) {
        return axios.post(API_BASE_URL, id);
    },

    getAllStatusTracking: async () => {
        return axios.get(API_BASE_URL);
    },

    getStatusTrackingById(id) {
        return axios.get(`${API_BASE_URL}/${id}`);
    },

    updateStatusTracking(currentStatusTracking, id) {
        return axios.put(`${API_BASE_URL}/${id}`, currentStatusTracking);
    },
    
    deleteStatusTracking(id) {
        return axios.delete(`${API_BASE_URL}/${id}`);
    }
};

export default StatusTrackingService;
