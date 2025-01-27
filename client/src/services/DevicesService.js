import axios from "axios";

const API_BASE_URL = import.meta.env.VITE_BASE_URI_DEVICES;

const DevicesService = {
  
    addNewDevice(id) {
        return axios.post(API_BASE_URL, id);
    },

    getAllDevices: async () => {
        return axios.get(API_BASE_URL);
    },

    getDeviceById(id) {
        return axios.get(`${API_BASE_URL}/${id}`);
    },

    updateDevice(currentDevice, id) {
        return axios.put(`${API_BASE_URL}/${id}`, currentDevice);
    },
    
    deleteDevice(id) {
        return axios.delete(`${API_BASE_URL}/${id}`);
    },
};

export default DevicesService