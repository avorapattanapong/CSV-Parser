"use client";

import {useEffect} from "react";
import {doGetLogs} from "@/redux/actions/logs";
import {useSelector} from "react-redux";
import {useAppDispatch} from "@/redux/hooks";
import {Space, Table, Tag} from "antd";
import {getProcessStatus} from "@/app/util/utils";
import {
    CloseCircleOutlined,
    ExclamationCircleOutlined,
    MinusCircleOutlined,
} from "@ant-design/icons";
import UploadCSVModal from "@/app/components/uploadCSVModal";
import ClearLogsModal from "@/app/components/deleteConfirmationModal";

export const ProcessTimeList = () => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(doGetLogs());
  }, []);
  const logs = useSelector(state => state.logs.logs);
  const logsLoading = useSelector(state => state.logs.loading);

  const getProcessStatusTag = (process) => {
    const status = getProcessStatus(process.startTime, process.endTime);
    switch (status) {
        case 'COMPLETE':
            return;
        case 'WARNING':
            return <Tag icon={<ExclamationCircleOutlined />} color="orange">Warning</Tag>;
        case 'ERROR':
            return <Tag icon={<CloseCircleOutlined />} color="red">Error</Tag>;
        default:
            return <Tag icon={<MinusCircleOutlined />}  color="default">Incomplete</Tag>
    }
  }
  const columns = [
    {
      title: 'PID',
      dataIndex: 'pid',
      key: 'pid',
    },
    {
      title: 'Description',
      dataIndex: 'description',
      key: 'description',
      render: (text) => <a>{text}</a>,
    },
    {
      title: 'Start Time',
      dataIndex: 'startTime',
      key: 'startTime',
    },
    {
      title: 'End Time',
      key: 'endTime',
      dataIndex: 'endTime'
    },
    {
      title: 'Status',
      key: 'status',
      render: (_, process) => (
          <Space size="middle">
            {getProcessStatusTag(process)}
          </Space>
      ),
    },
  ];

  return (
      <>
          <Table columns={columns} loading={logsLoading} dataSource={logs} />
      </>

  );
}