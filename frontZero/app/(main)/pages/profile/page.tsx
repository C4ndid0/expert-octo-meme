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
import { ProfileService } from '@/service/ProfileService';
import { error } from 'console';

const Profile = () => {
    let emptyProfile: Zero.Profile = {
        id: 0,
        description: ''
    };

    const [profiles, setProfiles] = useState<Zero.Profile[] | null>(null);
    const [profileDialog, setProfileDialog] = useState(false);
    const [deleteProfileDialog, setDeleteProfileDialog] = useState(false);
    const [deleteProfilesDialog, setDeleteProfilesDialog] = useState(false);
    const [profile, setProfile] = useState<Zero.Profile>(emptyProfile);
    const [selectedProfiles, setSelectedProfiles] = useState<Zero.Profile[]>([]);
    const [submitted, setSubmitted] = useState(false);
    const [globalFilter, setGlobalFilter] = useState('');
    const toast = useRef<Toast>(null);
    const dt = useRef<DataTable<any>>(null);
    const profileService = useMemo(() => new ProfileService(), []);

    useEffect(() => {
        if (!profiles) {
            profileService
                .listAll()
                .then((response) => {
                    setProfiles(response.data);
                })
                .catch((error) => {
                    console.log(error);
                });
        }
    }, [profileService, profiles]);

    const openNew = () => {
        setProfile(emptyProfile);
        setSubmitted(false);
        setProfileDialog(true);
    };

    const hideDialog = () => {
        setSubmitted(false);
        setProfileDialog(false);
    };

    const hideDeleteProfileDialog = () => {
        setDeleteProfileDialog(false);
    };

    const hideDeleteProfilesDialog = () => {
        setDeleteProfilesDialog(false);
    };

    const saveProfile = () => {
        setSubmitted(true);
        if (!profile.id) {
            profileService
                .insert(profile)
                .then((response) => {
                    setProfileDialog(false);
                    setProfile(emptyProfile);
                    setProfiles(null);
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
            profileService
                .update(profile)
                .then((response) => {
                    setProfileDialog(false);
                    setProfile(emptyProfile);
                    setProfiles(null);
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

    const editProfile = (profile: Zero.Profile) => {
        setProfile({ ...profile });
        setProfileDialog(true);
    };

    const confirmDeletedProfile = (profile: Zero.Profile) => {
        setProfile(profile);
        setDeleteProfileDialog(true);
    };

    const deleteProfile = () => {
        setProfile(emptyProfile);
        setDeleteProfileDialog(false);
        profileService
            .delete(profile.id)
            .then((response) => {
                setProfiles(null);
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
        setDeleteProfilesDialog(true);
    };

    const deleteSelectedProfile = () => {
        Promise.all(
            selectedProfiles.map(async (_profile) => {
                if (_profile.id) {
                    await profileService.delete(_profile.id);
                }
            })
        )
            .then((response) => {
                setProfiles(null);
                setSelectedProfiles([]);
                setDeleteProfilesDialog(false);
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
        let _profile = { ...profile };
        _profile[`${name}`] = val;
        setProfile(_profile);
    };

    const leftToolbarTemplate = () => {
        return (
            <React.Fragment>
                <div className="my-2">
                    <Button label="New" icon="pi pi-plus" severity="success" className=" mr-2" onClick={openNew} />
                    <Button label="Delete" icon="pi pi-trash" severity="danger" onClick={confirmDeleteSelected} disabled={!selectedProfiles || !(selectedProfiles as any).length} />
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

    const idBodyTemplate = (rowData: Zero.Profile) => {
        return (
            <>
                <span className="p-column-title">Code</span>
                {rowData.id}
            </>
        );
    };

    const descriptionBodyTemplate = (rowData: Zero.Profile) => {
        return (
            <>
                <span className="p-column-title">Description</span>
                {rowData.description}
            </>
        );
    };

    const actionBodyTemplate = (rowData: Zero.Profile) => {
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
            <Button label="Save" icon="pi pi-check" text onClick={saveProfile} />
        </>
    );
    const deleteProfileDialogFooter = (
        <>
            <Button label="No" icon="pi pi-times" text onClick={hideDeleteProfileDialog} />
            <Button label="Yes" icon="pi pi-check" text onClick={deleteProfile} />
        </>
    );
    const deleteProfilesDialogFooter = (
        <>
            <Button label="No" icon="pi pi-times" text onClick={hideDeleteProfilesDialog} />
            <Button label="Yes" icon="pi pi-check" text onClick={deleteSelectedProfile} />
        </>
    );

    return (
        <div className="grid crud-demo">
            <div className="col-12">
                <div className="card">
                    <Toast ref={toast} />
                    <Toolbar className="mb-4" left={leftToolbarTemplate} right={rightToolbarTemplate}></Toolbar>

                    <DataTable
                        ref={dt}
                        value={profiles}
                        selection={selectedProfiles}
                        onSelectionChange={(e) => setSelectedProfiles(e.value as any)}
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

                    <Dialog visible={profileDialog} style={{ width: '450px' }} header="Profile Details" modal className="p-fluid" footer={profileDialogFooter} onHide={hideDialog}>
                        <div className="field">
                            <label htmlFor="description">Description</label>
                            <InputText
                                id="description"
                                value={profile.description}
                                onChange={(e) => onInputChange(e, 'description')}
                                required
                                autoFocus
                                className={classNames({
                                    'p-invalid': submitted && !profile.description
                                })}
                            />
                            {submitted && !profile.description && <small className="p-invalid">Description is required.</small>}
                        </div>
                    </Dialog>

                    <Dialog visible={deleteProfileDialog} style={{ width: '450px' }} header="Confirm" modal footer={deleteProfileDialogFooter} onHide={hideDeleteProfileDialog}>
                        <div className="flex align-items-center justify-content-center">
                            <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: '2rem' }} />
                            {profile && (
                                <span>
                                    Are you sure you want to delete <b>{profile.description}</b>?
                                </span>
                            )}
                        </div>
                    </Dialog>

                    <Dialog visible={deleteProfilesDialog} style={{ width: '450px' }} header="Confirm" modal footer={deleteProfilesDialogFooter} onHide={hideDeleteProfilesDialog}>
                        <div className="flex align-items-center justify-content-center">
                            <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: '2rem' }} />
                            {profile && <span>Are you sure you want to delete the selected descriptions?</span>}
                        </div>
                    </Dialog>
                </div>
            </div>
        </div>
    );
};

export default Profile;
