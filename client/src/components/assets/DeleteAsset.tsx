
import { useNavigate, useParams } from "react-router-dom";
import { useGetAssetById, useDeleteAsset } from "../../services/AssetsService"

const DeleteAsset = () => {

    // Declare state variables
    const { id } = useParams();
    const navigate = useNavigate();
    const { mutate } = useDeleteAsset();
    const { data, isLoading } = useGetAssetById(id || "");

    // Handle loading state
    if (isLoading) {return <div>Loading...</div>;}
    if (!data) {return <div>No data found</div>;}

    // Handle Delete
    const handleDelete = () => {
        try {
            if (id) {
                mutate(id);
                navigate("/assets");
                window.location.reload();
            } else {
                console.error("Asset Id is undefined");
            }
        } catch (error) {
            console.error(error);
        }
    };

  
  return (
    <div>
      <h1>Delete Asset</h1>
      <p>Are you sure you want to delete this asset?</p>
      <button onClick={handleDelete}>Delete</button>
    </div>
  )
}

export default DeleteAsset