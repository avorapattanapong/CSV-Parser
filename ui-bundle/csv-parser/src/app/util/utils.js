import {
    ERROR_THRESHOLD,
    PROCESS_STATE,
    WARN_THRESHOLD
} from "@/app/util/constants";

export const getProcessStatus = (startTime, endTime) => {
    if (!startTime || !endTime) {
        return PROCESS_STATE.INCOMPLETE;
    }
    // TODO: Validate time format
    // Arbitrary date as we only care about time here.
    // TODO: Arbitrary date is risky, consider using a date object
    const dateString = "2019-01-01";
    const start = Date.parse(`${dateString}T${startTime}`);
    const end = Date.parse(`${dateString}T${endTime}`);

    const timeDifferenceMS = end - start;
    if (timeDifferenceMS > ERROR_THRESHOLD) {
        return PROCESS_STATE.ERROR;
    } else if (timeDifferenceMS > WARN_THRESHOLD) {
        return PROCESS_STATE.WARNING;
    } else {
        return PROCESS_STATE.COMPLETE;
    }
}