import React, { useState, useEffect, useMemo } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { createPortal } from "react-dom";
import Modal from "../layout/Modal";
import AssetService from "../../services/AssetsService";
import toast, { Toaster } from "react-hot-toast";
import UpdateAsset from "./UpdateAsset";
import DeleteAsset from "./DeleteAsset";

const AssetDetails = () => {

  const { id } = params;
  const navigate = useNavigate();
  const params = useParams();

  // State to store current asset data
  const [currentAsset, setCurrentAsset] = useState({});
  const [error, setError] = useState(null);

  // State to control modals
  const [isUpdateModalOpen, setIsUpdateModalOpen] = useState(false);
  const [isDeleteModalOpen, setIsDeleteModalOpen] = useState(false);


  // Function to toggle modals
  const toggleUpdateModal = () => {
    setIsUpdateModalOpen(!isUpdateModalOpen);
  };

  const toggleDeleteModal = () => {
    setIsDeleteModalOpen(!isDeleteModalOpen);
  };


  // Fetch current asset data using useEffect
  useEffect(() => {
    const fetchCurrentAsset = async () => {
      try {
        const response = await AssetService.getAssetById(id);
        setCurrentAsset(response.data);
      } catch (error) {
        setError(error.message);
        console.error(error);
      }
    };
    fetchCurrentAsset();
  }, [id]);

  // Memoize the current asset data to avoid unnecessary recalculations
  const memoizedCurrentAsset = useMemo(() => currentAsset, [currentAsset]);


  

  return( 
    <>
      AssetDetails
    </>
);
}

export default AssetDetails