package springapp.controller;

import aggregates.UserAggregate;
import entities.ShoppingBillEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import query_parser.QueryParser;
import repositories.ShoppingBillRepository;
import repository_bridges.ShoppingBillRepositoryBridge;
import verifier.FormDataVerifier;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Controller
public class FinanceController {

    @GetMapping("/")
    public String home(Model model, HttpSession session) throws SQLException {
        UserAggregate user = (UserAggregate) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        ShoppingBillRepository repo = new ShoppingBillRepositoryBridge();
        model.addAttribute("bills", repo.getAllShoppingBillsForUser(user.getUsername()));

        return "home";
    }

    @PostMapping("/addBill")
    public String addBill(HttpSession session, @RequestBody String a) throws SQLException {
        UserAggregate user = (UserAggregate) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        System.out.println(a);

        ShoppingBillRepository repo = new ShoppingBillRepositoryBridge();
        Map<String, List<String>> queryPairs = QueryParser.splitQuery(a);
        ShoppingBillEntity bill = FormDataVerifier.verifyBillData(queryPairs, user.getUsername());

        repo.addShoppingBill(bill.getShopName(), bill.getDate(), bill.getUser(), bill.getItems());
        return "redirect:/";
    }
}
