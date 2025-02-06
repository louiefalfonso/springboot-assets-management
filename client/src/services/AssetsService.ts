import axios from "axios";
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';

interface Asset { id: string; }

const API_BASE_URL = import.meta.env.VITE_BASE_URI_ASSETS;

const assetService = {

     addNewAsset: async (newAsset: Asset) => {
        const response = await axios.post(API_BASE_URL, newAsset);
        return response.data;
    },

    getAllAssets: async () => {
        const response = await axios.get(API_BASE_URL);
        return response.data;
    },

    getAssetById: async (id: string) => {
        const response = await axios.get(`${API_BASE_URL}/${id}`);
        return response.data;
    },

    updateCurrentAsset: async (currentAsset: Asset, id: string) => {
        const response = await axios.put(`${API_BASE_URL}/${id}`, currentAsset);
        return response.data;
    },

    deleteAsset: async (id: string) => {
        await axios.delete(`${API_BASE_URL}/${id}`);
    },

};


// React Query hooks
export const useGetAllAssets = () => {
  return useQuery( 
    { queryKey: ['assets'], queryFn: assetService.getAllAssets });
};

export const useGetAssetById = (id: string) => {
  return useQuery(
    { queryKey: ['asset', id], queryFn: () => assetService.getAssetById(id) });
};

export const useUpdateCurrentAsset = (id: string) => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: (currentAsset: Asset) => assetService.updateCurrentAsset(currentAsset, id),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['asset', id] });
    },
  });
};

export const useDeleteAsset = () => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: (id: string) => assetService.deleteAsset(id),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['assets'] });
    },
  });
};

export const useAddNewAsset = () => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: (newAsset: Asset) => assetService.addNewAsset(newAsset),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['assets'] });
    },
  });
};

export default assetService;





