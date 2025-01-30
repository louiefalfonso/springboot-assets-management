import axios from "axios";

const API_BASE_URL = import.meta.env.VITE_BASE_URI_WARRANTY_STATUS;

const WarrantyStatusService = {
    
    addNewWarrantyStatus(id) {
        return axios.post(API_BASE_URL, id);
    },

    getAllWarrantyStatus: async () => {
        return axios.get(API_BASE_URL);
    },

    getWarrantyStatusById(id) {
        return axios.get(`${API_BASE_URL}/${id}`);
    },

    updateWarrantyStatus(currentWarrantyStatus, id) {
        return axios.put(`${API_BASE_URL}/${id}`, currentWarrantyStatus);
    },
    
    deleteWarrantyStatus(id) {
        return axios.delete(`${API_BASE_URL}/${id}`);
    },
};
export default WarrantyStatusService;


