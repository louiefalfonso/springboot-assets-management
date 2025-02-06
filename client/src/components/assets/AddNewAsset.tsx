import React, { useState, useMemo } from "react";
import { useNavigate} from "react-router-dom";
import { useAddNewAsset } from "../../services/AssetsService"

const AddNewAsset = () => {

    // Declare state variables
    const navigate = useNavigate();
    const { mutate, data } = useAddNewAsset();

    // Declare state variables
    const [assetNumber, setAssetNumber] = useState('');
    const [brand, setBrand] = useState('');
    const [model, setModel] = useState('');
    const [type, setType] = useState('');
    const [serialNumber, setSerialNumber] = useState('');
    const [location, setLocation] = useState('');
    const [rackNumber, setRackNumber] = useState('');

    // Memoize the newAsset object
    const newAsset = useMemo(() => ({
      id: '',
      assetNumber,
      brand,
      model,
      type,
      serialNumber,
      location,
      rackNumber
    }), [assetNumber, brand, model, type, serialNumber, location, rackNumber]);

    // Handle form submission
    const handleSubmit = (e: React.FormEvent) => {
      e.preventDefault();
    if (!data) {return <div>No data found</div>;}

      try {
        mutate(newAsset);
        navigate("/assets");
        window.location.reload(); 
        
      } catch (error) {
        console.error(error);

      }
    };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Asset Number</label>
        <input type="text" value={assetNumber} onChange={(e) => setAssetNumber(e.target.value)} />
      </div>
      <div>
        <label>Brand</label>
        <input type="text" value={brand} onChange={(e) => setBrand(e.target.value)} />
      </div>
      <div>
        <label>Model</label>
        <input type="text" value={model} onChange={(e) => setModel(e.target.value)} />
      </div>
      <div>
        <label>Type</label>
        <input type="text" value={type} onChange={(e) => setType(e.target.value)} />
      </div>
      <div>
        <label>Serial Number</label>
        <input type="text" value={serialNumber} onChange={(e) => setSerialNumber(e.target.value)} />
      </div>
      <div>
        <label>Location</label>
        <input type="text" value={location} onChange={(e) => setLocation(e.target.value)} />
      </div>
      <div>
        <label>Rack Number</label>
        <input type="text" value={rackNumber} onChange={(e) => setRackNumber(e.target.value)} />
      </div>
      <button type="submit">Add Asset</button>
    </form>
  )
}

export default AddNewAsset