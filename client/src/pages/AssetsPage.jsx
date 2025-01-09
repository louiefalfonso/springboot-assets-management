import React from 'react'
import MainLayout from '../components/layout/MainLayout'
import Assets from '../components/assets/Assets'

const AssetsPage = () => {
  return (
    <>
      <MainLayout>
        <div className="flex flex-col gap-4 min-h-[calc(100vh-212px)]">
          <Assets/>
        </div>
      </MainLayout>  
    </>
  )
}

export default AssetsPage