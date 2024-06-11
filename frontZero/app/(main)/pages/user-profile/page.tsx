/* eslint-disable @next/next/no-img-element */
'use client';
import { Button } from 'primereact/button';
import { Column } from 'primereact/column';
import { DataTable } from 'primereact/datatable';
import { Dialog } from 'primereact/dialog';
import { FileUpload } from 'primereact/fileupload';
import { InputText } from 'primereact/inputtext';
import { Toast } from 'primereact/toast';
import { Toolbar } from 'primereact/toolbar';
import { classNames } from 'primereact/utils';
import React, { useEffect, useMemo, useRef, useState } from 'react';
import { Zero } from '@/types';
import { error } from 'console';
import { UserProfileService } from '@/service/UserProfileService';
import { UserService } from '@/service/userService';
import { ProfileService } from '@/service/ProfileService';
import { Dropdown, DropdownChangeEvent } from 'primereact/dropdown';

const UserProfile = () => {
    let emptyUserProfile: Zero.UserProfile = {
        id: 0,
        profile: { description: '' },
        user: { name: '', login: '', password: '', email: '' }
    };

    const [usersProfiles, setUsersProfiles] = useState<Zero.UserProfile[] | null>(null);
    const [userProfileDialog, setUserProfileDialog] = useState(false);
    const [deleteProfileDialog, setDeleteUserProfileDialog] = useState(false);
    const [deleteUsersProfilesDialog, setDeleteUsersProfilesDialog] = useState(false);

    const [userProfile, setUserProfile] = useState<Zero.UserProfile>(emptyUserProfile);
    const [selectedUsersProfiles, setUsersSelectedProfiles] = useState<Zero.UserProfile[]>([]);
    const [submitted, setSubmitted] = useState(false);
    const [globalFilter, setGlobalFilter] = useState('');
    const toast = useRef<Toast>(null);
    const dt = useRef<DataTable<any>>(null);
    const userProfileService = useMemo(() => new userProfileService(), []);
    const userService = useMemo(() => new UserService(), []);
    const profileService = useMemo(() => new ProfileService(), []);
    const [users, setUsers] = useState<Zero.User[] | null>(null);
    const [profiles, setProfiles] = useState<Zero.Profile[] | null>(null);

    useEffect(() => {
        if (!usersProfiles) {
            userProfileService
                .listAll()
                .then((response) => {
                    setUsersProfiles(response.data);
                })
                .catch((error) => {
                    console.log(error);
                });
        }
    }, [userProfileService, usersProfiles]);

    useEffect(() => {
        if (userProfileDialog) {
            userService
                .listAll()
                .then((response) => setUsers(response.data))
                .catch((error) => {
                    toast.current?.show({
                        severity: 'info',
                        summary: 'Error',
                        detail: 'Error on listed profiles'
                    });
                });
            profileService
                .listAll()
                .then((response) => setProfiles(response.data))
                .catch((error) => {
                    toast.current?.show({
                        severity: 'info',
                        summary: 'Error',
                        detail: 'Error on linsted profiles'
                    });
                });
        }
    }, [userProfileDialog]);

    const openNew = () => {
        setUserProfile(emptyUserProfile);
        setSubmitted(false);
        setUserProfileDialog(true);
    };

    const hideDialog = () => {
        setSubmitted(false);
        setUserProfileDialog(false);
    };

    const hideDeleteUserProfileDialog = () => {
        setDeleteUserProfileDialog(false);
    };

    const hideDeleteUsersProfilesDialog = () => {
        setDeleteUsersProfilesDialog(false);
    };

    const saveUserProfile = () => {
        setSubmitted(true);
        if (!userProfile.id) {
            userProfileService
                .insert(userProfile)
                .then((response) => {
                    setUserProfileDialog(false);
                    setUserProfile(emptyUserProfile);
                    setUsersProfiles(null);
                    toast.current?.show({
                        severity: 'success',
                        summary: 'Successful',
                        detail: 'Profile Created'
                    });
                })
                .catch((error) => {
                    console.log(error);
                    toast.current?.show({
                        severity: 'error',
                        summary: 'Error!',
                        detail: 'Error at save profile' + error
                    });
                });
        } else {
            userProfileService
                .update(userProfile)
                .then((response) => {
                    setUserProfileDialog(false);
                    setUserProfile(emptyUserProfile);
                    setUsersProfiles(null);
                    toast.current?.show({
                        severity: 'success',
                        summary: 'Successful',
                        detail: 'Profile Updated'
                    });
                })
                .catch((error) => {
                    console.log(error);
                    toast.current?.show({
                        severity: 'error',
                        summary: 'Error!',
                        detail: 'Error at save profile' + error
                    });
                });
        }
    };

    const editProfile = (userprofile: Zero.UserProfile) => {
        setUserProfile({ ...userprofile });
        setUserProfileDialog(true);
    };

    const confirmDeletedProfile = (userprofile: Zero.UserProfile) => {
        setUserProfile(userprofile);
        setDeleteUserProfileDialog(true);
    };

    const deleteUserProfile = () => {
        setUserProfile(emptyUserProfile);
        setDeleteUserProfileDialog(false);
        userProfileService
            .delete(userProfile.id)
            .then((response) => {
                setUsersProfiles(null);
                toast.current?.show({
                    severity: 'success',
                    summary: 'Successful',
                    detail: 'Profile Deleted'
                });
            })
            .catch((error) => {
                toast.current?.show({
                    severity: 'error',
                    summary: 'Error!',
                    detail: 'Error at delete profile' + error
                });
            });
    };

    const exportCSV = () => {
        dt.current?.exportCSV();
    };

    const confirmDeleteSelected = () => {
        setDeleteUsersProfilesDialog(true);
    };

    const deleteSelectedUserProfile = () => {
        Promise.all(
            selectedUsersProfiles.map(async (_profile) => {
                if (_profile.id) {
                    await userProfileService.delete(_profile.id);
                }
            })
        )
            .then((response) => {
                setUsersProfiles(null);
                setUsersSelectedProfiles([]);
                setDeleteUsersProfilesDialog(false);
                toast.current?.show({
                    severity: 'success',
                    summary: 'Successful',
                    detail: 'Profile Deleted'
                });
            })
            .catch((error) => {
                toast.current?.show({
                    severity: 'error',
                    summary: 'Error!',
                    detail: 'Error at delete profile' + error
                });
            });
    };

    const onInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>, name: string) => {
        const val = (e.target && e.target.value) || '';
        let _profile = { ...userProfile };
        _profile[`${name}`] = val;
        setUserProfile(_profile);
    };

    const leftToolbarTemplate = () => {
        return (
            <React.Fragment>
                <div className="my-2">
                    <Button label="New" icon="pi pi-plus" severity="success" className=" mr-2" onClick={openNew} />
                    <Button label="Delete" icon="pi pi-trash" severity="danger" onClick={confirmDeleteSelected} disabled={!selectedUsersProfiles || !(selectedUsersProfiles as any).length} />
                </div>
            </React.Fragment>
        );
    };

    const rightToolbarTemplate = () => {
        return (
            <React.Fragment>
                <FileUpload mode="basic" accept="image/*" maxFileSize={1000000} chooseLabel="Import" className="mr-2 inline-block" />
                <Button label="Export" icon="pi pi-upload" severity="help" onClick={exportCSV} />
            </React.Fragment>
        );
    };

    const idBodyTemplate = (rowData: Zero.UserProfile) => {
        return (
            <>
                <span className="p-column-title">Code</span>
                {rowData.id}
            </>
        );
    };

    const descriptionBodyTemplate = (rowData: Zero.UserProfile) => {
        return (
            <>
                <span className="p-column-title">Description</span>
                {rowData.profile}
            </>
        );
    };

    const actionBodyTemplate = (rowData: Zero.UserProfile) => {
        return (
            <>
                <Button icon="pi pi-pencil" rounded severity="success" className="mr-2" onClick={() => editProfile(rowData)} />
                <Button icon="pi pi-trash" rounded severity="warning" onClick={() => confirmDeletedProfile(rowData)} />
            </>
        );
    };

    const header = (
        <div className="flex flex-column md:flex-row md:justify-content-between md:align-items-center">
            <h5 className="m-0">Manage Profile</h5>
            <span className="block mt-2 md:mt-0 p-input-icon-left">
                <i className="pi pi-search" />
                <InputText type="search" onInput={(e) => setGlobalFilter(e.currentTarget.value)} placeholder="Search..." />
            </span>
        </div>
    );

    const profileDialogFooter = (
        <>
            <Button label="Cancel" icon="pi pi-times" text onClick={hideDialog} />
            <Button label="Save" icon="pi pi-check" text onClick={saveUserProfile} />
        </>
    );
    const deleteProfileDialogFooter = (
        <>
            <Button label="No" icon="pi pi-times" text onClick={hideDeleteUserProfileDialog} />
            <Button label="Yes" icon="pi pi-check" text onClick={deleteUserProfile} />
        </>
    );
    const deleteProfilesDialogFooter = (
        <>
            <Button label="No" icon="pi pi-times" text onClick={hideDeleteUsersProfilesDialog} />
            <Button label="Yes" icon="pi pi-check" text onClick={deleteSelectedUserProfile} />
        </>
    );
    const onSelectProfileChange = (profile: Zero.Profile) => {
        let _userProfile = { ...userProfile };
        _userProfile.profile = profile;
        setUserProfile(_userProfile);
    };
    const onSelectUserChange = (user: Zero.User) => {
        let _userProfile = { ...userProfile };
        _userProfile.user = user;
        setUserProfile(_userProfile);
    };

    return (
        <div className="grid crud-demo">
            <div className="col-12">
                <div className="card">
                    <Toast ref={toast} />
                    <Toolbar className="mb-4" left={leftToolbarTemplate} right={rightToolbarTemplate}></Toolbar>

                    <DataTable
                        ref={dt}
                        value={usersProfiles}
                        selection={selectedUsersProfiles}
                        onSelectionChange={(e) => setUsersSelectedProfiles(e.value as any)}
                        dataKey="id"
                        paginator
                        rows={10}
                        rowsPerPageOptions={[5, 10, 25]}
                        className="datatable-responsive"
                        paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown"
                        currentPageReportTemplate="Showing {first} to {last} of {totalRecords} profiles"
                        globalFilter={globalFilter}
                        emptyMessage="No profile found."
                        header={header}
                        responsiveLayout="scroll"
                    >
                        <Column selectionMode="multiple" headerStyle={{ width: '4rem' }}></Column>
                        <Column field="id" header="Code" sortable body={idBodyTemplate} headerStyle={{ minWidth: '15rem' }}></Column>
                        <Column field="description" header="Description" sortable body={descriptionBodyTemplate} headerStyle={{ minWidth: '15rem' }}></Column>

                        <Column body={actionBodyTemplate} headerStyle={{ minWidth: '10rem' }}></Column>
                    </DataTable>

                    {/* <Dialog visible={userProfileDialog} style={{ width: '450px' }} header="Profile Details" modal className="p-fluid" footer={profileDialogFooter} onHide={hideDialog}>
                        <div className="field">
                            <label htmlFor="profile">Profile</label>
                            <Dropdown optionLabel="description" value={userProfile.profile} options={profiles} filter onChange={(e: DropdownChangeEvent) => onSelectProfileChange(e.value)} placeholder="Select a profile..." />
                            {submitted && !userProfile.profile && <small className="p-invalid">Description is required.</small>}
                        </div>

                        <div className="field">
                            <label htmlFor="name">User</label>
                            <Dropdown optionLabel="name" value={userProfile.user} options={users} filter onChange={(e: DropdownChangeEvent) => onSelectUserChange(e.value)} placeholder="Select a user..." />
                            {submitted && !userProfile.profile && <small className="p-invalid">Description is required.</small>}
                        </div>
                    </Dialog> */}

                    <Dialog visible={deleteProfileDialog} style={{ width: '450px' }} header="Confirm" modal footer={deleteProfileDialogFooter} onHide={hideDeleteUserProfileDialog}>
                        <div className="flex align-items-center justify-content-center">
                            <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: '2rem' }} />
                            {userProfile && (
                                <span>
                                    Are you sure you want to delete <b>{userProfile.id}</b>?
                                </span>
                            )}
                        </div>
                    </Dialog>

                    <Dialog visible={deleteUsersProfilesDialog} style={{ width: '450px' }} header="Confirm" modal footer={deleteProfilesDialogFooter} onHide={hideDeleteUsersProfilesDialog}>
                        <div className="flex align-items-center justify-content-center">
                            <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: '2rem' }} />
                            {userProfile && <span>Are you sure you want to delete the selected descriptions?</span>}
                        </div>
                    </Dialog>
                </div>
            </div>
        </div>
    );
};

export default UserProfile;
