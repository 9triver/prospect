export interface ILeaveApplicationInfo {
  id?: number;
  startDate?: string | null;
  endDate?: string | null;
  leaveType?: string | null;
  reason?: string | null;
  status?: string | null;
}

export class LeaveApplicationInfo implements ILeaveApplicationInfo {
  constructor(
    public id?: number,
    public startDate?: string | null,
    public endDate?: string | null,
    public leaveType?: string | null,
    public reason?: string | null,
    public status?: string | null,
  ) {}
}
