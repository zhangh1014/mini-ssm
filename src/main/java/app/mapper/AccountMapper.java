package app.mapper;

import java.util.List;

import app.model.AccountModel;

public interface AccountMapper {
    public int insAccount(AccountModel accountModel);
    public int delAccountByPk(AccountModel accountModel);
    public int updAccountByPk(AccountModel accountModel);
    
    public AccountModel selAccountByPk(AccountModel accountModel);
    public List<AccountModel> selAccountByCondition(AccountModel accountModel);
}
