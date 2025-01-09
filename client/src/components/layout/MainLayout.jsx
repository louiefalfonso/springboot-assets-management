import React, { useState } from 'react'
import Sidebar from './Sidebar'
import Topbar from './Topbar'
import Footer from './Footer'

const MainLayout = ({ children }) => {
  const [isSidebarSize, setIsSidebarSize] = useState(false);

  const toggleSidebarCollapse = () => {
    document.body.classList.toggle("toggle-sidebar");
    setIsSidebarSize(!isSidebarSize);
  };
  return (
    <>
      <React.Fragment>
        <div className="bg-[#f9fbfd] dark:bg-dark text-black">
          <div className="bg-black min-h-[220px] sm:min-h-[250px] bg-bottom fixed hidden w-full -z-50 detached-img"></div>

          {/* Start Menu Sidebar Overlay */}
          <div
            className={`fixed inset-0 bg-black/60 dark:bg-dark/90 z-[999] lg:hidden ${
              isSidebarSize ? "" : "hidden"
            }`}
            onKeyDown={toggleSidebarCollapse}
            onClick={toggleSidebarCollapse}
          ></div>
          {/* End Menu Sidebar Overlay */}

          <div className="flex mx-auto main-container">
            <Sidebar isSidebarSize={isSidebarSize} />
            <div className="flex-1 main-content">
              <Topbar toggleSidebarCollapse={toggleSidebarCollapse} />
              <div className="h-[calc(100vh-60px)] relative overflow-y-auto overflow-x-hidden p-4 space-y-4 detached-content">
                {children}
                <Footer />
              </div>
            </div>
          </div>
        </div>
      </React.Fragment>
    </>
  );
}

export default MainLayout