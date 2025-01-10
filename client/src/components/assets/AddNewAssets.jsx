import React, { useState, useEffect, useMemo } from "react";
import { useNavigate, useParams } from "react-router-dom";
import toast, { Toaster } from "react-hot-toast";
import moment from "moment";
import AssetService from "../../services/AssetsService";

const AddNewAssets = () => {
  const navigate = useNavigate();
  const params = useParams();
  const { id } = params;

  const [assetNumber, setAssetNumber] = useState("");
  const [brand, setBrand] = useState("");
  const [model, setModel] = useState("");
  const [type, setType] = useState("");
  const [serialNumber, setSerialNumber] = useState("");
  const [location, setLocation] = useState("");
  const [rackNumber, setRackNumber] = useState("");

  const [error, setError] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);

  const toggleModal = () => {
    setIsModalOpen(!isModalOpen);
  };

  // Memoize the newAsset object
  const addNewAsset = useMemo(() => {
    return {
      assetNumber,
      brand,
      model,
      type,
      serialNumber,
      location,
      rackNumber,
    };
  }, [assetNumber, brand, model, type, serialNumber, location, rackNumber]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    AssetService.addNewAsset(addNewAsset)
      .then(() => {
        navigate("/assets");
        toast.success("Asset added successfully!");
      })
      .catch((error) => {
        setError(error.response.data.message);
      });
  };

  return (
    <>
      <div className="grid grid-cols-1 gap-4 md:grid-cols-1">
        <div className="p-5 bg-white border rounded border-black/10 dark:bg-darklight dark:border-darkborder">
          <form
            onSubmit={handleSubmit}
            onClick={(e) => e.stopPropagation()}
            className="grid grid-cols-1 sm:grid-cols-2 gap-4"
          >
            <div className="sm:col-span-1">
              <label
                htmlFor="assetNumber"
                className="block text-sm font-medium text-gray-900"
              >
                Asset Number:
              </label>
              <input
                placeholder="Asset Number"
                required
                type="text"
                className="form-input"
                id="assetNumber"
                value={assetNumber}
                onChange={(e) => setAssetNumber(e.target.value)}
              />
            </div>
          </form>
        </div>
      </div>
      <Toaster duration={12000} />
    </>
  );
}

export default AddNewAssets