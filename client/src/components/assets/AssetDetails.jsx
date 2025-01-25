import React, { useState, useEffect, useMemo } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { createPortal } from "react-dom";
import Modal from "../layout/Modal";
import AssetService from "../../services/AssetsService";
import toast, { Toaster } from "react-hot-toast";
import UpdateAsset from "./UpdateAsset";
import DeleteAsset from "./DeleteAsset";

const AssetDetails = () => {

  const navigate = useNavigate();
  const params = useParams();
  const { id } = params;

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

  return (
    <>
      <div className="flex flex-col gap-4 min-h-[calc(100vh-212px)]">
        <div className="p-5 bg-white border rounded border-black/10 dark:bg-darklight dark:border-darkborder">
          <div className="flex items-center justify-between">
            <h2 className="font-bold">Asset Details</h2>
            <button
              type="button"
              onClick={() => window.history.back()}
              className="btn py-1 px-3.5 text-xs bg-warning border border-warning rounded-md text-black transition-all duration-300 hover:bg-warning/[0.85] hover:border-warning/[0.85]"
            >
              Back
            </button>
          </div>
        </div>
        <div className="grid grid-cols-1 gap-4 md:grid-cols-2">
          <div className="p-5 bg-white border rounded border-black/10 dark:bg-darklight dark:border-darkborder">
            {Object.keys(currentAsset).length > 0 ? (
              <div className="space-y-4">
                <div className="overflow-auto" key={currentAsset}>
                  <table className="w-full mt-4">
                    <tbody>
                      <tr className="ltr:text-left rtl:text-right">
                        <td className="font-bold">Asset Number:</td>
                        <td>{currentAsset.assetNumber}</td>
                      </tr>
                      <tr className="ltr:text-left rtl:text-right">
                        <td className="font-bold">Model:</td>
                        <td className="break-words whitespace-normal">
                          {currentAsset.model}
                        </td>
                      </tr>
                      <tr className="ltr:text-left rtl:text-right">
                        <td className="font-bold">Brand:</td>
                        <td>{currentAsset.brand}</td>
                      </tr>
                      <tr className="ltr:text-left rtl:text-right">
                        <td className="font-bold">Rack Number:</td>
                        <td>{currentAsset.rackNumber}</td>
                      </tr>
                      <tr className="ltr:text-left rtl:text-right">
                        <td className="font-bold">Location:</td>
                        <td>{currentAsset.location}</td>
                      </tr>
                      <tr className="ltr:text-left rtl:text-right">
                        <td className="font-bold">Type:</td>
                        <td>{currentAsset.type}</td>
                      </tr>
                      <tr className="ltr:text-left rtl:text-right">
                        <td className="font-bold">Serial Number:</td>
                        <td>{currentAsset.serialNumber}</td>
                      </tr>
                      <tr className="ltr:text-left rtl:text-right">
                        <td className="font-bold">Date Created:</td>
                        <td>{currentAsset.dateCreated}</td>
                      </tr>

                      <tr className="ltr:text-left rtl:text-right">
                        <td>
                          <button
                            type="button"
                            onClick={toggleUpdateModal}
                            className="btn py-1 px-3.5  mr-2 text-xs bg-info border border-info border-info rounded-md text-white transition-all duration-300 hover:bg-info/[0.85] hover:border-info/[0.85]"
                          >
                            Update
                          </button>
                          <button
                            type="button"
                            onClick={toggleDeleteModal}
                            className="btn py-1 px-3.5 text-xs bg-danger border border-danger border-danger rounded-md text-white transition-all duration-300 hover:bg-danger/[0.85] hover:border-danger/[0.85]"
                          >
                            Delete
                          </button>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>
            ) : (
              <div className="space-y-4">
                <p>No Details Found</p>
              </div>
            )}
          </div>

          <div className="p-5 bg-white border rounded border-black/10 dark:bg-darklight dark:border-darkborder"></div>
        </div>
      </div>
      {isUpdateModalOpen &&
        createPortal(
          <Modal
            isOpen={isUpdateModalOpen}
            toggleModal={toggleUpdateModal}
            title="Update Asset Details"
            divClass="flex items-start justify-center min-h-screen px-4"
            content={<UpdateAsset toggleModal={toggleUpdateModal} />}
            sizeClass="relative w-full max-w-lg p-0 my-8 overflow-hidden bg-white border rounded-lg border-black/10 dark:bg-darklight dark:border-darkborder"
            spaceClass="p-5 space-y-4"
          />,
          document.body
        )}
      {isDeleteModalOpen &&
        createPortal(
          <Modal
            isOpen={isDeleteModalOpen}
            toggleModal={toggleDeleteModal}
            title="Delete Asset"
            divClass="flex items-center justify-center min-h-screen px-4"
            content={<DeleteAsset toggleModal={toggleDeleteModal} />}
            sizeClass="relative w-full max-w-lg p-0 my-8 overflow-hidden bg-white border rounded-lg border-black/10 dark:bg-darklight dark:border-darkborder"
            spaceClass="p-5 space-y-4"
          />,
          document.body
        )}
    </>
  );
}

export default AssetDetails