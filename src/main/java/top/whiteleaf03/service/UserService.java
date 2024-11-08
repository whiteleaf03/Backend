package top.whiteleaf03.service;

import top.whiteleaf03.modal.entity.User;
import top.whiteleaf03.utils.Result;

/**
 * @author WhiteLeaf03
 */
public interface UserService {
    User getCurrentUser();

    Result login(String username, String password);
}
