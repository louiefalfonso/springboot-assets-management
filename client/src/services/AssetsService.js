import axios from "axios";

const API_BASE_URL = import.meta.env.VITE_BASE_URI_ASSETS;

const AssetsService = {

    addNewAsset(id){
        return axios.post(API_BASE_URL, id);
    }, 

    getAllAssets: async ()=> {
        return axios.get(API_BASE_URL);
    },

    getAssetById(id){
        return axios.get(`${API_BASE_URL}/${id}`);
    },

    updateAsset(currentAsset, id){
        return axios.put(`${API_BASE_URL}/${id}`, currentAsset);
    },

    deleteAsset(id){
        return axios.delete(`${API_BASE_URL}/${id}`);
    }
  
};

export default AssetsService