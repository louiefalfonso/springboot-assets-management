import { useParams } from "react-router-dom";
import { useGetAssetById} from "../../services/AssetsService"

interface AssetDetailsProps {
  assetId: string | undefined;
}

const AssetDetails: React.FC<AssetDetailsProps> = ({ assetId }) => {

    // Declare state variables
    const { id } = useParams();
    const { data, isLoading } = useGetAssetById(id || "");

    // Handle loading state
    if (isLoading) {return <div>Loading...</div>;}
    if (!data) {return <div>No data found</div>;}

  return (
    <div>
      <h1>Asset Details</h1>
      <p>Asset ID: {assetId}</p>
      <p>Asset Number: {data.assetNumber}</p>
      <p>Brand: {data.brand}</p>
      <p>Model: {data.model}</p>
      <p>Type: {data.type}</p>
      <p>Serial Number: {data.serialNumber}</p>
      <p>Location: {data.location}</p>
      <p>Rack Number: {data.rackNumber}</p>
    </div>
  );
}

export default AssetDetails