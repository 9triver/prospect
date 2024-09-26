export interface ILeaveApplicationInfo {
  id?: string;
  startDate?: string | null;
  endDate?: string | null;
  leaveType?: string | null;
  reason?: string | null;
  status?: string | null;
}

export class LeaveApplicationInfo implements ILeaveApplicationInfo {
  constructor(
    public id?: string,
    public startDate?: string | null,
    public endDate?: string | null,
    public leaveType?: string | null,
    public reason?: string | null,
    public status?: string | null,
  ) {}
}