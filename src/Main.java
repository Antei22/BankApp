import classes.Account;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) 
    {
        Scanner scan = new Scanner(System.in);
        ArrayList<Account> accounts = new ArrayList<>();

        Account current = selectAccount(scan, accounts);

        while (true) {
            try {
                System.out.println("\n------------- BANK MENU -------------");
                System.out.println("Current account: ID: " + current.getId() + " Owner: " + current.getOwner());
                System.out.println("1. Show all accounts");
                System.out.println("2. Deposit money");
                System.out.println("3. Withdraw money");
                System.out.println("4. Transfer to another account");
                System.out.println("5. Show balance");
                System.out.println("6. Show transactions");
                System.out.println("7. Find transactions");
                System.out.println("8. Switch account");
                System.out.println("9. Delete account");
                System.out.println("10. Exit program.");
                System.out.print("\nChoose option: ");

                int choice = scan.nextInt();
                scan.nextLine();

                switch (choice) {
                    case 1:
                        if (accounts.isEmpty()) {
                            System.out.println("\nNo accounts available yet. Please create a new one.");
                        } else {
                            System.out.println("\n--------------------------");
                            for (Account a : accounts) {
                                System.out.println("ID: " + a.getId() + " Owner: " + a.getOwner());
                            }
                        }
                        break;

                    case 2:
                        boolean vd = false; 
                        float depAmount = 0.0f;

                        while (!vd) {
                            try {
                                System.out.print("Enter deposit amount: ");
                                depAmount = scan.nextFloat();
                                scan.nextLine();
                                vd = true;
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please enter a number.");
                                scan.nextLine(); 
                            }
                        }
                        current.deposit(depAmount);
                        break;

                    case 3:
                        boolean vw = false; 
                        float wAmount = 0.0f;

                        while (!vw) {
                            try {
                                System.out.print("Enter withdraw amount: ");
                                wAmount = scan.nextFloat();
                                scan.nextLine();
                                vw = true;
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please enter a number.");
                                scan.nextLine(); 
                            }
                        }
                        current.withdraw(wAmount);
                        break;
                        
                    case 4:
                        if (accounts.size() < 2) {
                            System.out.println("\nYou need at least 2 accounts to make a transfer.");
                            break;
                        }

                        int toId = -1;
                        float tAmount = 0.0f;
                        boolean vt = false; 
                        boolean va = false; 

                        while (!vt) {
                            try {
                                System.out.print("Enter recipient account ID: ");
                                toId = scan.nextInt();
                                scan.nextLine();
                                vt = true;
                            } catch (Exception e) {
                            System.out.println("Invalid input. Please enter a number.");
                            scan.nextLine(); 
                            }
                        }

                        Account toAccount = null;
                        for (Account a : accounts) {
                            if (a.getId() == toId) {
                                toAccount = a;
                                break;
                            }
                        }

                        if (toAccount == null) {
                            System.out.println("No account found with ID " + toId);
                        } else if (toAccount == current) {
                            System.out.println("Can not transfer to the same account.");
                            break;
                        }
                        
                        while (!va) {
                            try {
                                System.out.print("Enter amount to transfer: ");
                                tAmount = scan.nextFloat();
                                scan.nextLine();
                                va = true;
                            } catch (Exception e) {
                            System.out.println("Invalid input. Please enter a number.");
                            scan.nextLine(); 
                            }
                        }

                        current.transfer(toAccount, tAmount);
                        break;


                    case 5:
                        current.showBalance();
                        break;

                    case 6:
                        current.showTransactions();
                        break;

                    case 7:
                        System.out.println("\n--------- FIND TRANSACTIONS ---------");
                        System.out.println("1. By ID");
                        System.out.println("2. By type");
                        System.out.println("3. By date");
                        System.out.println("4. By amount range");

                        int findChoice = -1;

                        try {
                            System.out.print("Choose an option: ");
                            findChoice = scan.nextInt();
                            scan.nextLine();
                        } catch (Exception e) {
                            System.out.println("Invalid input. Please enter a number.");
                            scan.nextLine();
                            break;
                        }

                        switch (findChoice) {

                            case 1:
                                try {
                                    System.out.print("Enter transaction ID: ");
                                    int id = scan.nextInt();
                                    scan.nextLine();
                                    current.findTransactionsById(id);
                                } catch (Exception e) {
                                    System.out.println("Invalid input. Please enter a valid number.");
                                    scan.nextLine();
                                }
                                break;

                            case 2:
                                try {
                                    System.out.print("Enter transaction type (deposit / withdraw / transfer in / transfer out): ");
                                    String type = scan.nextLine();
                                    current.findTransactionsByType(type);
                                } catch (Exception e) {
                                    System.out.println("Invalid input. Please enter a text value.");
                                    scan.nextLine();
                                }
                                break;

                            case 3:
                                try {
                                    System.out.print("Enter date (YYYY-MM-DD): ");
                                    String date = scan.nextLine();
                                    current.findTransactionsByTime(date);
                                } catch (Exception e) {
                                    System.out.println("Invalid input. Please enter a correct date format.");
                                    scan.nextLine();
                                }
                                break;

                            case 4:
                                float min = 0.0f;
                                float max = 0.0f;
                                boolean vmin = false;
                                boolean vmax = false;

                                while (!vmin) {
                                    try {
                                        System.out.print("Enter minimum amount: ");
                                        min = scan.nextFloat();
                                        scan.nextLine();
                                        vmin = true;
                                    } catch (Exception e) {
                                        System.out.println("Invalid input. Please enter a number.");
                                        scan.nextLine();
                                    }
                                }

                                while (!vmax) {
                                    try {
                                        System.out.print("Enter maximum amount: ");
                                        max = scan.nextFloat();
                                        scan.nextLine();
                                        vmax = true;
                                    } catch (Exception e) {
                                        System.out.println("Invalid input. Please enter a number.");
                                        scan.nextLine();
                                    }
                                }

                                current.findTransactionsBySumm(min, max);
                                break;

                            default:
                                System.out.println("Invalid option. Try again.");
                                break;
                        }
                        break;

                    case 8:
                        current = selectAccount(scan, accounts);
                        break;
                    
                    case 9:
                        System.out.println("Are you sure you want to delete this account (y/n)?");
                        String confirm = scan.nextLine();
                        if (confirm.equals("y")) {
                            accounts.remove(current);
                            System.out.println("Are deleted successfully.");
                            if (accounts.isEmpty()) {
                                System.out.println("\nNo accounts left. Please create a new one.");
                            }
                            current = selectAccount(scan, accounts);
                        } else {
                            System.out.println("Deletion cancelled.");
                        }
                        break;

                    case 10:
                        System.out.println("Exit program.");
                        return;


                    default:
                        System.out.println("Invalid opyion. Please try again.");
                }

            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scan.nextLine(); 
            }
        }
    }

    private static Account selectAccount(Scanner scan, ArrayList<Account> accounts) {
            Account current = null;

            while (current == null) {
                try {
                    System.out.println("\n--------- ACCOUNT SELECTION ---------");
                    System.out.println("1. Choose existing account");
                    System.out.println("2. Create new account");
                    int choice = scan.nextInt();
                    scan.nextLine();

                    switch (choice) {
                        case 1:
                            if (accounts.isEmpty()) {
                                System.out.println("\nNo accounts available yet. Please create a new one.");
                                break;
                            }

                            for (Account a : accounts) {
                                System.out.println("ID: " + a.getId() + " Owner: " + a.getOwner());
                            }

                            System.out.print("Enter account ID to login: ");
                            int id = scan.nextInt();
                            scan.nextLine();

                            boolean found = false;
                            for (Account a : accounts) {
                                if (a.getId() == id) {
                                    current = a;
                                    found = true;
                                    System.out.println("ID: " + a.getId() + " Owner: " + a.getOwner());
                                    break;
                                }
                            }
                            if (!found) {
                                System.out.println("No account found with that ID.");
                            } else {
                                break;
                            }
                            break;

                        case 2:
                            System.out.print("Enter owner name: ");
                            String owner = scan.nextLine();

                            Account newAcc = new Account(owner);
                            accounts.add(newAcc);
                            current = newAcc;
                            System.out.println("\nAccount created successfully.");
                            System.out.println("ID: " + newAcc.getId() + " Owner: " + newAcc.getOwner());
                            break;

                        default:
                            System.out.println("Invalid option. Try again.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scan.nextLine(); 
                }
            }
            return current;
        }
    }