import { useGetAllAssets } from '../../services/AssetsService'
import { Link } from "react-router-dom";

const AssetList = () => {

    // Declare state variables
    const { data, isLoading } = useGetAllAssets();
        
    // Handle loading state
    if (isLoading) {return <div>Loading...</div>;}
    if (!data) {return <div>No data found</div>;}

  return (
    <>
      <h1>Asset List</h1>
      <ul>
        {data.map(
          (asset: {
            id: string;
            assetNumber: string;
            brand: string;
            serialNumber: string;
            type: string;
            model: string;
            location: string;
            rackNumber: string;
          }) => (
            <li key={asset.id}>
              <div>
                {asset.id} | {asset.assetNumber} | {asset.brand} |
                {asset.serialNumber} |{asset.type} | {asset.model} |
                {asset.location} | {asset.rackNumber}
                <button
                  type="button"
                  className="p-3 bg-purple border border-purple rounded-md text-white transition-all duration-300 hover:bg-purple/[0.85] hover:border-purple/[0.85]"
                >
                  <Link to={`/assets/${asset.id}`}>
                    View
                  </Link>
                </button>
              </div>
            </li>
          )
        )}
      </ul>
    </>
  );
}

export default AssetList