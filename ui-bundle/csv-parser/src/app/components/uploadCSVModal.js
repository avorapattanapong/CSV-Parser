"use client";

import React, {useState} from 'react';
import {Button, Modal, Upload, message} from 'antd';
import {doGetLogs, doUploadCSV} from "@/redux/actions/logs";
import {useDispatch, useSelector} from "react-redux";
import {UploadOutlined} from "@ant-design/icons";

const UploadCSVModal = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const dispatch = useDispatch();
  const loading = useSelector(state => state.logs.loading);
  const error = useSelector(state => state.logs.error);
  const [fileToBeUploaded, setFileToBeUploaded] = useState();
  const [messageApi, contextHolder] = message.useMessage();

  const props = {
    onRemove: file => {
      setFileToBeUploaded(null);
    },
    beforeUpload: file => {
      setFileToBeUploaded(file);
      return false;
    },
    fileList: fileToBeUploaded ? [fileToBeUploaded] : [],
  };

  const showModal = () => {
    setIsModalOpen(true);
  };
  const handleOk = () => {
    dispatch(doUploadCSV(fileToBeUploaded))
      .then(() => {
        setIsModalOpen(false);
        dispatch(doGetLogs());
      })
      .catch(() => {
          messageApi.open({
              type: 'error',
              content: error,
          });
      });
  };
  const handleCancel = () => {
    setIsModalOpen(false);
  };
  return (
      <>
          {contextHolder}
        <Button type="primary" onClick={showModal}>
          Upload New CSV
        </Button>
        <Modal
          title="Upload New CSV"
          open={isModalOpen}
          onOk={handleOk}
          okButtonProps={{
            disabled: !fileToBeUploaded
          }}
          onCancel={handleCancel}
          loading={loading}
        >
          <div style={{marginTop: '15px'}}>
              <Upload {...props}>
                  <Button icon={<UploadOutlined />}>Select File</Button>
              </Upload>
          </div>
        </Modal>
      </>
  );
};
export default UploadCSVModal;