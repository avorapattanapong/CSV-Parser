"use client";
import React, {useState} from 'react';
import {Button, Modal} from 'antd';
import {doClearLogs, doGetLogs} from "@/redux/actions/logs";
import {useDispatch, useSelector} from "react-redux";

const ClearLogsModal = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const dispatch = useDispatch();
  const loading = useSelector(state => state.logs.loading);

  const showModal = () => {
    setIsModalOpen(true);
  };
  const handleOk = () => {
    dispatch(doClearLogs()).then(() => {
      setIsModalOpen(false);
      dispatch(doGetLogs());
    });
  };
  const handleCancel = () => {
    setIsModalOpen(false);
  };
  return (
      <>
        <Button color="danger" variant="outlined" onClick={showModal}>
          Clear Logs
        </Button>
        <Modal title="Confirm Clear Logs" open={isModalOpen} onOk={handleOk} onCancel={handleCancel} loading={loading}>
          <p>Are you sure you want clear all logs?</p>
        </Modal>
      </>
  );
};
export default ClearLogsModal;