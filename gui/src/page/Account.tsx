import React, {useEffect, useState} from 'react';
import axios from 'axios';

const Account = () => {

    const [account, setAccountInfo] = useState(null);
    const [error, setError] = useState("");
    const [editMode, setEditMode] = useState(false);
    const [editedName, setEditedName] = useState("");
    const [selectedFile, setSelectedFile] = useState(null);

    const userId = sessionStorage.getItem("id");
    const fileName = sessionStorage.getItem("photoId");

    useEffect(() => {
        const fetchAccountInfo = async () => {
            try {
                const accountDto = { id: userId };
                const response = await axios.post('/api/manage/my-account', accountDto);
                setAccountInfo(response.data);
            } catch (error) {
                setError('Error fetching account information');
                console.error('Error fetching account information', error);
            }
        };

        if (userId) {
            fetchAccountInfo();
        }
    }, [userId]);

    const onFileChange = (event: any) => {
        setSelectedFile(event.target.files[0]);
    };

    const onUpload = async () => {
        if(selectedFile){
        const formData = new FormData();
        formData.append('file', selectedFile);
        formData.append('photoId', fileName + ".jpeg");
        try {
            await axios.post('/api/unauthorized/profile-image', formData);
            window.location.reload();
        } catch (error) {
            console.error('Error uploading file:', error);
        }
        } else {
            console.log('No file selected');
        }
    };

    const handlePatchName = async () => {
        try {
            const response = await axios.patch('/api/unauthorized/edit-data', { user_id: userId, name: editedName });
            // @ts-ignore
            setAccountInfo({...account, userDto: {...account.userDto, name: editedName}});
            setEditMode(false);
        } catch (error) {
            setError('Error updating name');
            console.error('Error updating name', error);
        }
    };

    const renderEditView = () => {
        const isNameEmpty = editedName === '';
        return (
            <>
                <input
                    type="text"
                    value={editedName}
                    onChange={(e) => setEditedName(e.target.value)}
                />
                {isNameEmpty && <div>Uzupełnij Pole</div>}
                <button
                    onClick={handlePatchName}
                    disabled={isNameEmpty}
                >
                    Zapisz zmiany
                </button>
            </>
        );
    };

    const renderDisplayView = () => {
    if (!account) return null;
    return <><p>Imię: {account["userDto"]["name"]}</p>
        </>
    };

    if (error) {
        return <div>{error}</div>;
    }

    if (!account) {
        return <div>Loading...</div>;
    }

    return (
        <div className='account-container'>
        <div className='account-info-card'>
            <h2>Informacje o Koncie</h2>
            <p>Email: {account["accountDto"]["mail"]}</p>
            <p>Saldo: {account["accountDto"]["money"]}</p>
            <p>Rola: {account["accountDto"]["role"]}</p>
            {editMode ? renderEditView() : renderDisplayView()}
            <p>Points: {account["userDto"]["score"]}</p>
            <p></p>
            <button
                className="details-button"
                onClick={() => {
                    setEditMode(true);
                    setEditedName(account["userDto"]["name"]);
                }}
            >
                Przejście w tryb edycji
            </button>
            <p>
            { editMode && <>
                <button
                    className="details-button"
                    onClick={() => {
                        setEditMode(false);
                    }}
                >
                    Anuluj
                </button>
            </>
           }
            </p>
        </div>
            <div className="image-upload-section">
                <h3>Prześlij swoje zdjęcie</h3>
                <input type="file" onChange={onFileChange} />
                <button onClick={onUpload}>Prześlij zdjęcie</button>

            </div>
        </div>

    );
};

export default Account;