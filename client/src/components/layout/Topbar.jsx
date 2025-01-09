import React from 'react'

const Topbar = ({ toggleSidebarCollapse }) => {
  return (
    <>
      <div className="bg-white dark:bg-darklight dark:border-darkborder flex gap-4 lg:z-10 items-center justify-between px-4 h-[60px] border-b border-black/10 detached-topbar relative">
        <div className="flex items-center flex-1 gap-2 sm:gap-4">
          <button
            type="button"
            className="text-black dark:text-white/80"
            onClick={toggleSidebarCollapse}
          >
            <svg
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 24 24"
              className="w-6 h-6"
            >
              <path
                d="M3 4H21V6H3V4ZM3 11H15V13H3V11ZM3 18H21V20H3V18Z"
                fill="currentColor"
              ></path>
            </svg>
          </button>
        </div>
      </div>
    </>
  );
};

export default Topbar