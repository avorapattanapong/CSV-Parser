import {ProcessTimeList} from "@/app/components/processTimeList";
import {Content} from "antd/es/layout/layout";
import React from "react";
import UploadCSVModal from "@/app/components/uploadCSVModal";
import ClearLogsModal from "@/app/components/deleteConfirmationModal";

export default function Home() {

  return (
    <>
      <h1 style={{fontSize: "25pt", fontWeight: "500", margin: "25px 50px"}}>Process Time Logs</h1>
      <Content
          style={{
            padding: '25px 50px',
            background: "#fff",
            minHeight: 280,
          }}
      >
        <div style={{ padding: "25px 0" }}>
          <span style={{ marginRight: "10px"}}>
            <UploadCSVModal />
          </span>
          <span style={{ marginRight: "10px"}}>
            <ClearLogsModal />
          </span>
        </div>
        <ProcessTimeList />
      </Content>
    </>
  );
}
