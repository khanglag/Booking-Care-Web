package com.example.Booking_Care_Web.Controllers;

import com.example.Booking_Care_Web.Models.Dtos.CheckupPackpageDTO;
import com.example.Booking_Care_Web.Models.Dtos.UserDTO;
import com.example.Booking_Care_Web.Models.Entities.Account;
import com.example.Booking_Care_Web.Models.Entities.CheckupPackpage;
import com.example.Booking_Care_Web.Models.Entities.MedicalRecord;
import com.example.Booking_Care_Web.Models.Entities.Role;
import com.example.Booking_Care_Web.Services.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class AdminController {
//    @Autowired
//    private UserService userService;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountServiceImp accountServiceImp;

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    private CheckupPackpageServiceImpl checkupPackpageServiceImpl;
    @GetMapping("/adminPage")
    public String showAdminPage(Model model, Authentication authentication, HttpSession session) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            User user = (User) principal;
            String username = user.getUsername();
            Account account = accountService.findByUsername(username);
            com.example.Booking_Care_Web.Models.Entities.User user_normal = userServiceImpl.findById(account.getAccountId());
            session.setAttribute("user", user_normal);
            session.setAttribute("userID", user_normal.getUserId());
            session.setAttribute("authentication", authentication);
            model.addAttribute("user", user_normal);
        }
        return "admin/adminPage";
    }

    @GetMapping("/userManagement")
    public String userManagement(Model model, Authentication authentication, HttpSession session) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            User user = (User) principal;
            String username = user.getUsername();
            Account account = accountService.findByUsername(username);
            com.example.Booking_Care_Web.Models.Entities.User user_normal = userServiceImpl.findById(account.getAccountId());
            session.setAttribute("user", user_normal);
            session.setAttribute("userID", user_normal.getUserId());
            session.setAttribute("authentication", authentication);
            model.addAttribute("user", user_normal);
        }
        List<UserDTO> listUser = userService.findAll();
        model.addAttribute("listUser", listUser);
        return "admin/userManagement";
    }
    @GetMapping("/checkupPackageManagement")
    public String checkupPackageManagement(Model model, Authentication authentication, HttpSession session) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            User user = (User) principal;
            String username = user.getUsername();
            Account account = accountService.findByUsername(username);
            com.example.Booking_Care_Web.Models.Entities.User user_normal = userServiceImpl.findById(account.getAccountId());
            session.setAttribute("user", user_normal);
            session.setAttribute("userID", user_normal.getUserId());
            session.setAttribute("authentication", authentication);
            model.addAttribute("user", user_normal);
        }
        List<CheckupPackpageDTO> checkupPackpages = checkupPackpageServiceImpl.findAll();
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        Map<String, String> formattedAmounts = checkupPackpages.stream()
                .collect(Collectors.toMap(
                        CheckupPackpageDTO::getPackage_id,
                        packageDTO -> decimalFormat.format(packageDTO.getAmount())
                ));
        model.addAttribute("checkupPackages", checkupPackpages);
        model.addAttribute("formattedAmounts", formattedAmounts);

        return "admin/checkupPackageManagement";
    }

//    @GetMapping("/statisticManagement")
//    public String statisticManagement(Model model, Authentication authentication, HttpSession session) {
//        authentication = SecurityContextHolder.getContext().getAuthentication();
//        Object principal = authentication.getPrincipal();
//        if (principal instanceof UserDetails) {
//            User user = (User) principal;
//            String username = user.getUsername();
//            Account account = accountService.findByUsername(username);
//            com.example.Booking_Care_Web.Models.Entities.User user_normal = userServiceImpl.findById(account.getAccountId());
//            session.setAttribute("user", user_normal);
//            session.setAttribute("userID", user_normal.getUserId());
//            session.setAttribute("authentication", authentication);
//            model.addAttribute("user", user_normal);
//        }
////        List<UserDTO> listUser = userService.findAll();
////        model.addAttribute("listUser", listUser);
//        return "admin/statisticManagement";
//    }
    @PostMapping(value = "userManagement",consumes = "application/json")
    public Account registerAdmin(@RequestBody Map<String, Object> requestData,Model model, Authentication authentication, HttpSession session) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            User user = (User) principal;
            String username = user.getUsername();
            Account account = accountService.findByUsername(username);
            com.example.Booking_Care_Web.Models.Entities.User user_normal = userServiceImpl.findById(account.getAccountId());
            session.setAttribute("user", user_normal);
            session.setAttribute("userID", user_normal.getUserId());
            session.setAttribute("authentication", authentication);
            model.addAttribute("user", user_normal);
        }
        String email = (String) requestData.get("email");
        String name = (String) requestData.get("name");
        Map<String, Object> accountData = (Map<String, Object>) requestData.get("account");
        String username = (String) accountData.get("username");
        String password = (String) accountData.get("password");
        String roleId = (String) accountData.get("role_id");
        Role role = new Role();
        role.setRoleId(roleId);
        String userID = "";
        if(roleId.equals("patient")){
            userID =   userServiceImpl.createNewUserId("pt");
        }
        if(roleId.equals("admin00")){
            userID =   userServiceImpl.createNewUserId("admin");
        }
        if(roleId.equals("support")){
            userID =   userServiceImpl.createNewUserId("sp");
        }
        if(roleId.equals("doctor0")){
            userID =   userServiceImpl.createNewUserId("doctor");
        }
        com.example.Booking_Care_Web.Models.Entities.User user = new com.example.Booking_Care_Web.Models.Entities.User();
        user.setEmail(email);
        user.setName(name);
        user.setUserId(userID);
        Account account = new Account();
        account.setAccountId(userID);
        account.setUsername(username);
        account.setPassword(password);
        account.setRole(role);
        user.setAccount(account);
        userServiceImpl.saveUser(user);
        return   accountServiceImp.saveAccount(account);
    }
    @PostMapping(value = "/editUser",consumes = "application/json")
    public String updateUserAdmin(@RequestBody com.example.Booking_Care_Web.Models.Entities.User user_edit, Model model,Authentication authentication, HttpSession session) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            User user = (User) principal;
            String username = user.getUsername();
            Account account = accountService.findByUsername(username);
            com.example.Booking_Care_Web.Models.Entities.User user_normal = userServiceImpl.findById(account.getAccountId());
            session.setAttribute("user", user_normal);
            session.setAttribute("userID", user_normal.getUserId());
            session.setAttribute("authentication", authentication);
            model.addAttribute("user", user_normal);
        }
        user_edit.setAddress(user_edit.getAddress());
        user_edit.setPhoneNumber(user_edit.getPhoneNumber());
        user_edit.setIdentificationCard(user_edit.getIdentificationCard());
        user_edit.setGender(user_edit.getGender());
        userServiceImpl.updateUser(user_edit.getUserId(), user_edit);
        return "admin/userManagement";
    }

    @PostMapping(value = "/editCheckupPackage",consumes = "application/json")
    public String updateCheckupPackage(@RequestBody Map<String, Object> requestData, Model model,Authentication authentication, HttpSession session) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            User user = (User) principal;
            String username = user.getUsername();
            Account account = accountService.findByUsername(username);
            com.example.Booking_Care_Web.Models.Entities.User user_normal = userServiceImpl.findById(account.getAccountId());
            session.setAttribute("user", user_normal);
            session.setAttribute("userID", user_normal.getUserId());
            session.setAttribute("authentication", authentication);
            model.addAttribute("user", user_normal);
        }
        String id = (String) requestData.get("package_id");
        String name = (String) requestData.get("name");
        String description = (String) requestData.get("description");
        String amount = (String) requestData.get("amount");
        double amountDouble = 0.0;
        try {
            amountDouble = Double.parseDouble(amount);
        } catch (NumberFormatException e) {
            System.err.println("Không thể chuyển đổi giá trị amount thành double: " + e.getMessage());
        }

        CheckupPackpage checkupPackpage = new CheckupPackpage();
        checkupPackpage.setName(name);
        checkupPackpage.setDescription(description);
        checkupPackpage.setAmount(amountDouble);
        checkupPackpageServiceImpl.updateCP(id,checkupPackpage);
        return "admin/checkupPackageManagement";
    }

    @PostMapping(value = "/checkupPackageManagement",consumes = "application/json")
    public String addCheckupPackage(@RequestBody Map<String, Object> requestData, Model model,Authentication authentication, HttpSession session) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            User user = (User) principal;
            String username = user.getUsername();
            Account account = accountService.findByUsername(username);
            com.example.Booking_Care_Web.Models.Entities.User user_normal = userServiceImpl.findById(account.getAccountId());
            session.setAttribute("user", user_normal);
            session.setAttribute("userID", user_normal.getUserId());
            session.setAttribute("authentication", authentication);
            model.addAttribute("user", user_normal);
        }
        String name = (String) requestData.get("name");
        String description = (String) requestData.get("desc");
        String amount = (String) requestData.get("amount");
        double amountDouble = 0.0;
        try {
            amountDouble = Double.parseDouble(amount);
        } catch (NumberFormatException e) {
            System.err.println("Không thể chuyển đổi giá trị amount thành double: " + e.getMessage());
        }
        String id = checkupPackpageServiceImpl.createCPId();
        CheckupPackpage checkupPackpage = new CheckupPackpage();
        checkupPackpage.setPackageId(id);
        checkupPackpage.setName(name);
        checkupPackpage.setDescription(description);
        checkupPackpage.setAmount(amountDouble);
        checkupPackpageServiceImpl.saveCheckupPackpage(checkupPackpage);
        return "admin/checkupPackageManagement";
    }
}
