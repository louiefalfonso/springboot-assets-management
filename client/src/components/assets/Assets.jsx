import React, { useState, useEffect, useMemo } from "react";
import { createPortal } from "react-dom";
import { Link } from "react-router-dom";
import Modal from "../layout/Modal";
import AssetService from "../../services/AssetsService";
import AddNewAssets from "./AddNewAssets";

import { RiFileSearchLine, RiAddCircleLine } from "react-icons/ri";

const Assets = () => {

  // Declare state variables
  const [assets, setAssets] = useState([]);

  // Pagination state variables
  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 5;

  // State to control modals
  const [isModalOpen, setIsModalOpen] = useState(false);

  // Function to toggle modal
  const toggleModal = () => {
    setIsModalOpen(!isModalOpen);
  };

  // Function to handle page change
  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  // Memoize the assets array to prevent unnecessary recalculations
  const memoizedAssets = useMemo(() => assets, [assets]);

  // Get current assets
  const indexOfLastItem = currentPage * itemsPerPage;
  const indexOfFirstItem = indexOfLastItem - itemsPerPage;
  const currentAssets = memoizedAssets.slice(indexOfFirstItem, indexOfLastItem);

  // Calculate total pages
  const totalPages = Math.ceil(memoizedAssets.length / itemsPerPage);

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
                className="btn flex items-center gap-1.5 bg-success border border-success rounded-md text-white transition-all duration-300 hover:bg-success/[0.85] hover:border-success/[0.85]"
              >
                <RiAddCircleLine />
                Add New Asset
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
                  {currentAssets.map((asset) => (
                    <tr key={asset.id}>
                      <td>{asset.assetNumber}</td>
                      <td>{asset.brand}</td>
                      <td>{asset.serialNumber}</td>
                      <td className="break-words whitespace-normal">
                        {asset.model}
                      </td>
                      <td>{asset.type}</td>
                      <td>
                        <button
                          type="button"
                          className="p-3 bg-purple border border-purple rounded-md text-white transition-all duration-300 hover:bg-purple/[0.85] hover:border-purple/[0.85]"
                        >
                          <Link to={`/assets/${asset.id}`}>
                            <RiFileSearchLine />
                          </Link>
                        </button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </div>
          <div className="p-5 bg-white border rounded border-black/10 dark:bg-darklight dark:border-darkborder">
            <div className="overflow-auto ">
              <div className="flex justify-center">
                <ul className="inline-flex items-end">
                  {Array.from({ length: totalPages }, (_, index) => (
                    <li key={index + 1} className="mx-1">
                      <button
                        onClick={() => handlePageChange(index + 1)}
                        className={`flex justify-center px-3 py-2 rounded transition text-muted hover:text-purple border border-black/10 hover:border-purple dark:border-darkborder dark:text-darkmuted dark:hover:text-purple dark:hover:border-purple ${
                          currentPage === index + 1
                            ? "rounded transition text-white bg-purple border border-purple"
                            : ""
                        }`}
                      >
                        {index + 1}
                      </button>
                    </li>
                  ))}
                </ul>
              </div>
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