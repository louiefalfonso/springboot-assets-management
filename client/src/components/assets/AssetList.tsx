import { useGetAllAssets } from '../../services/AssetsService'

const AssetList = () => {

    const { data, isLoading } = useGetAllAssets();
    
    if (isLoading) {return <div>Loading...</div>;}
    if (!data) {return <div>No data found</div>;}

  return (
    <>
      <h1>Asset List</h1>
      <ul>
        {data.map((asset: { id: string; assetNumber: string; brand: string, serialNumber: string, type: string  }) => (
          <li key={asset.id}>
            <div>{asset.id} | {asset.assetNumber} | {asset.brand} | {asset.serialNumber} |{asset.type}</div>
          </li>
        ))}
      </ul>
    </>
  );
}

export default AssetList