import React from 'react'
import { useParams } from "react-router-dom";
import MainLayout from '../../../components/layout/MainLayout';
import AssetDetails from '../../../components/assets/AssetDetails';

const AssetPage = () => {

  // Get the asset ID from the URL
  const { id } = useParams();

  return (
    <>
      <MainLayout>
        <AssetDetails assetId={id} />
      </MainLayout>
    </>
  );
}

export default AssetPage