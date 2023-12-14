interface AccountDto {
    id: number | null;
    mail: string;
    money: number;
    role: 'USER' | string;
    password: string | null;
    photoId: string | null;
    enabled: boolean;
}

interface UserDto {
    user_id: number | null;
    name: string | null;
    score: number | null;
    photoId: string | null;
    lastName: string | null;
    pesel: string | null;
    phone: string | null;
}

export interface PersonalInformation {
    accountDto: AccountDto;
    userDto: UserDto;
}