import React, { useState, useEffect, useMemo } from "react";
import { useNavigate, useParams } from "react-router-dom";
import toast, { Toaster } from "react-hot-toast";
import moment from "moment";
import AssetService from "../../services/AssetsService";

const UpdateAsset = () => {
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

  
  return (
    <div>UpdateAsset</div>
  )
}

export default UpdateAsset