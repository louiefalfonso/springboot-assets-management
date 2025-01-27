import React, { useState, useEffect, useMemo } from "react";
import { createPortal } from "react-dom";
import { Link } from "react-router-dom";
import Modal from "../layout/Modal";
import AssetService from "../../services/AssetsService";
import AddNewAssets from "./AddNewAssets";

const Assets = () => {
  
  const [assets, setAssets] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);

  const toggleModal = () => {
    setIsModalOpen(!isModalOpen);
  };

  // Fetch asset data using useEffect
  useEffect(() => {
    const fetchAssets = async () => {
      try {
        const response = await AssetService.getAllAssets();
        setAssets(response.data);
      } catch (error) {
        console.error(error);
      }
    };
    fetchAssets();
  }, []);

  // Memoize the assets array to prevent unnecessary recalculations
  const memoizedAssets = useMemo(() => assets, [assets]);

  return (
    <>
      <div className="flex flex-col gap-4 min-h-[calc(100vh-212px)]">
        <div className="grid grid-cols-1 gap-4">
          <div className="p-5 bg-white border rounded border-black/10 dark:bg-darklight dark:border-darkborder">
            <div className="flex items-center justify-between">
              <h2 className="font-bold">Assets List</h2>
              <button
                type="button"
                onClick={toggleModal}
                className="btn py-1 px-3.5 text-xs bg-warning border border-warning rounded-md text-black transition-all duration-300 hover:bg-warning/[0.85] hover:border-warning/[0.85]"
              >
                + Add Assets
              </button>
            </div>
            <div className="overflow-auto">
              <table className="w-full mt-4 table-striped">
                <thead>
                  <tr className="ltr:text-left rtl:text-right">
                    <th>Asset Number</th>
                    <th>Brand</th>
                    <th>Serial Number</th>
                    <th>Model</th>
                    <th>Type</th>
                    <th></th>
                  </tr>
                </thead>
                <tbody className="text-center">
                  {memoizedAssets.map((asset) => (
                    <tr key={asset.id}>
                      <td>{asset.assetNumber}</td>
                      <td>{asset.brand}</td>
                      <td>{asset.serialNumber}</td>
                      <td className="break-words whitespace-normal">
                        {asset.model}
                      </td>
                      <td>{asset.type}</td>
                      <td>
                        <Link
                          to={`/assets/${asset.id}`}
                          className="btn py-1 px-3.5 text-xs bg-info border border-info rounded-md text-black transition-all duration-300 hover:bg-info/[0.85] hover:border-info/[0.85]"
                        >
                          View
                        </Link>
                      </td>
                    </tr>
                  ))}
                </tbody>
                
              </table>
            </div>
          </div>
        </div>
      </div>
      {isModalOpen &&
        createPortal(
          <Modal
            isOpen={isModalOpen}
            toggleModal={toggleModal}
            title="Create New Asset"
            divClass="flex items-start justify-center min-h-screen px-4"
            content={<AddNewAssets toggleModal={toggleModal} />}
            sizeClass="relative w-full max-w-lg p-0 my-8 overflow-hidden bg-white border rounded-lg border-black/10 dark:bg-darklight dark:border-darkborder"
            spaceClass="p-5 space-y-4"
          />,
          document.body
        )}
    </>
  );
}

export default Assets