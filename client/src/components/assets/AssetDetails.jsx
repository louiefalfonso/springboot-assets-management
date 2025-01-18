import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { createPortal } from "react-dom";
import Modal from "../layout/Modal";
import AssetService from "../../services/AssetsService";
import UpdateAsset from "./UpdateAsset";
import DeleteAsset from "./DeleteAsset";

const AssetDetails = () => {
  
  const navigate = useNavigate();
  const params = useParams();
  const { id } = params;


  return (
    <>AssetDetails</>
  )
}

export default AssetDetails