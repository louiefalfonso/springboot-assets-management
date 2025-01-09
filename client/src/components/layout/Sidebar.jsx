import React, { useState } from "react";
import { Link, useLocation } from "react-router-dom";
import { sidebarData } from "../data/sidebarData";
import logo from "../../assets/logo.png";

const Sidebar = ({ isSidebarSize }) => {
  const [activeMenu, setActiveMenu] = useState(null);
  const location = useLocation();

  const toggleSubMenu = (key) => {
    setActiveMenu(activeMenu === key ? null : key);
  };

  return (
    <>
      <React.Fragment>
        <nav
          className={`sidebar fixed z-[9999] flex-none w-[240px] border-r border-black/10 transition-all duration-300 ${
            isSidebarSize ? "block" : "hidden"
          } md:block`}
        >
          <div className="h-full bg-white dark:bg-darklight">
            <div className="p-4">
              <Link to="index" className="w-full h-44 main-logo">
                <img
                  src={logo}
                  className="mx-auto dark-logo logo  h-16  dark:hidden"
                  alt="logo"
                />
                <img
                  src={logo}
                  className="hidden mx-auto light-logo h-16 logo dark:block"
                  alt="logo"
                />
              </Link>
            </div>
            <div className="h-[calc(100vh-60px)]  overflow-y-auto overflow-x-hidden px-5 pb-4 space-y-16 detached-menu">
              <ul className="relative flex flex-col gap-4">
                {(sidebarData || []).map((item, key) => (
                  <React.Fragment key={key}>
                    {item.isTitle ? (
                      <h2 className="my-2 text-sm text-black/50 dark:text-white/30">
                        <span>{item.label}</span>
                      </h2>
                    ) : item.subItems ? (
                      <>
                        {isSidebarSize && (
                          <li className="menu nav-item">
                            <Link
                              to="#"
                              className={`items-center justify-between text-black nav-link group ${
                                activeMenu === key ||
                                item.subItems.some(
                                  (subitem) =>
                                    location.pathname === subitem.link
                                )
                                  ? "active"
                                  : ""
                              }`}
                              onClick={() => toggleSubMenu(key)}
                            >
                              <div className="flex items-center">
                                <svg
                                  xmlns="http://www.w3.org/2000/svg"
                                  viewBox="0 0 24 24"
                                  className="w-5 h-5"
                                >
                                  <path
                                    d={item.icon}
                                    fill="currentColor"
                                  ></path>
                                </svg>
                                <span className="ltr:pl-1.5 rtl:pr-1.5">
                                  {item.label}
                                </span>
                              </div>
                              <div
                                className={`flex items-center justify-center w-4 h-4 dropdown-icon ${
                                  activeMenu === key ? "!rotate-180" : ""
                                }`}
                              >
                                <svg
                                  xmlns="http://www.w3.org/2000/svg"
                                  viewBox="0 0 24 24"
                                  className="w-6 h-6"
                                >
                                  <path
                                    d="M11.9997 13.1714L16.9495 8.22168L18.3637 9.63589L11.9997 15.9999L5.63574 9.63589L7.04996 8.22168L11.9997 13.1714Z"
                                    fill="currentColor"
                                  ></path>
                                </svg>
                              </div>
                            </Link>
                          </li>
                        )}
                        <ul
                          style={{
                            display:
                              isSidebarSize && activeMenu === key ? "" : "none",
                          }}
                          className="flex flex-col gap-1 text-black sub-menu dark:text-white/60"
                        >
                          {item.subItems.map((subitem, subkey) => (
                            <React.Fragment key={subkey}>
                              {!subitem.subItems && (
                                <li>
                                  <Link
                                    to={subitem.link}
                                    className={
                                      location.pathname === subitem.link
                                        ? "active"
                                        : ""
                                    }
                                  >
                                    {subitem.label}
                                  </Link>
                                </li>
                              )}
                            </React.Fragment>
                          ))}
                        </ul>
                      </>
                    ) : (
                      <li className="menu nav-item">
                        <Link
                          to={item.link}
                          className={`items-center justify-between text-black nav-link group ${
                            location.pathname === item.link ? "active" : ""
                          }`}
                          onClick={() => toggleSubMenu(key)}
                        >
                          <div className="flex items-center">
                            <svg
                              xmlns="http://www.w3.org/2000/svg"
                              viewBox="0 0 24 24"
                              className="w-5 h-5"
                            >
                              <path d={item.icon} fill="currentColor"></path>
                            </svg>
                            <span className="ltr:pl-1.5 rtl:pr-1.5">
                              {item.label}
                            </span>
                          </div>
                        </Link>
                      </li>
                    )}
                  </React.Fragment>
                ))}
              </ul>
            </div>
          </div>
        </nav>
      </React.Fragment>
    </>
  );
};

export default Sidebar;
