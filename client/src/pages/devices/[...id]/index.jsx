
import React from "react";
import { useParams } from "react-router-dom";
import MainLayout from "../../../components/layout/MainLayout";

const DevicePage = () => {
    
    // Get the asset ID from the URL
    const { id } = useParams();


  return (
    <>
    <MainLayout>

    </MainLayout>
    </>
  )
}

export default DevicePage;