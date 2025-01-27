import axios from "axios";

const API_BASE_URL = import.meta.env.VITE_BASE_URI_ASSETS;

const AssetsService = {
  
  async addNewAsset(id) {
    try {
      const response = await axios
        .post(API_BASE_URL, id);
      return response;
    } catch (error) {
      if (error.response.status === 400) {
        const errorMessage = error.response.data.message;
        if (errorMessage.includes("Asset number already exists")) {
          throw new Error("Asset number already exists");
        } else {
          throw error;
        }
      } else {
        throw error;
      }
    }
  },

  getAllAssets: async () => {
    return axios.get(API_BASE_URL);
  },

  getAssetById(id) {
    return axios.get(`${API_BASE_URL}/${id}`);
  },

  updateAsset(currentAsset, id) {
    return axios.put(`${API_BASE_URL}/${id}`, currentAsset);
  },

  deleteAsset(id) {
    return axios.delete(`${API_BASE_URL}/${id}`);
  },
};

export default AssetsService