import React, { useState, useEffect, useMemo } from "react";
import { useNavigate, useParams } from "react-router-dom";
import toast, { Toaster } from "react-hot-toast";
import AssetService from "../../services/AssetsService";

const DeleteAsset = () => {
  
  // Declare state variables
  const { id } = useParams();
  const navigate = useNavigate();
  const [assets, setAssets] = useState([]);

  // Fetch asset data using useEffect
  useEffect(() => {
    const fetchAsset = async () => {
      const response = await AssetService.getAssetById(id);
      setAssets(response.data);
    };

    fetchAsset();
  }, [id]);

  // Memoize the asset data to avoid unnecessary recalculations
  const memoizedAssets = useMemo(() => assets, [assets]);

  // Handle asset deletion
  const deleteAsset = async (id) => {
    try {
      await AssetService.deleteAsset(id);
      toast.success("Deleted Asset Successfully!");
      navigate("/assets");
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <>
      <div className="grid grid-cols-1 gap-4 md:grid-cols-1">
        <div className="relative p-4 text-center border rounded border-danger bg-danger/10">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 24 24"
            className="w-12 h-12 mx-auto"
          >
            <path
              d="M12 22C6.47715 22 2 17.5228 2 12C2 6.47715 6.47715 2 12 2C17.5228 2 22 6.47715 22 12C22 17.5228 17.5228 22 12 22ZM12 20C16.4183 20 20 16.4183 20 12C20 7.58172 16.4183 4 12 4C7.58172 4 4 7.58172 4 12C4 16.4183 7.58172 20 12 20ZM11 7H13V9H11V7ZM11 11H13V17H11V11Z"
              fill="#F1416C"
            ></path>
          </svg>
          <h1 className="text-danger text-lg">Delete Asset Details</h1>
          <h1 className="text-danger">
            Are you sure you want to delete this Asset?
            <br />
            You will not be able to recover this asset information.
          </h1>
        </div>
        <button
          className="btn bg-danger border border-danger rounded-md text-white transition-all duration-300 hover:bg-danger/[0.85] hover:border-danger/[0.85]"
          onClick={() => deleteAsset(memoizedAssets.id)}
        >
          Confirm Delete
        </button>
      </div>
    </>
  );
}

export default DeleteAsset