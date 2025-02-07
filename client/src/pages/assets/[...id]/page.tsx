import { useParams } from "react-router-dom";
import AssetDetails from "../../../components/assets/AssetDetails";

const AssetPage = () => {

   // Get the asset ID from the URL
  const { id } = useParams();

  return (
    <>
        <AssetDetails assetId={id}/>
    </>
  )
}

export default AssetPage