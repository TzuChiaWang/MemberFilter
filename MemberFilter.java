import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * MemberFilter
 */
interface InnerMemberFilter {
    boolean accept(Member m);
}

class VIPFilter implements InnerMemberFilter {
    public boolean accept(Member m) {
        return m.isVIP();
    }
}

class SexFilter implements InnerMemberFilter {
    private Sex sex;

    public SexFilter(Sex sex) {
        this.sex = sex;
    }

    public boolean accept(Member m) {
        return m.getSex() == sex;
    }
}

class BloodTypeFilter implements InnerMemberFilter {
    private BloodType bloodType;

    public BloodTypeFilter(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public boolean accept(Member m) {
        return m.getBloodType() == bloodType;
    }
}

public class MemberFilter {
    public static void main(String args[]) {
        Date d1 = new Date(1988, 3, 10);
        Member m1 = new Member("Steph Liu", "091919191", d1, Sex.MALE, BloodType.O, false);
        Member m2 = new Member("Jennifer Chen", "092919191", new Date(2001, 5, 7), Sex.FEMALE, BloodType.B, true);
        Member m3 = new Member("John Doe", "092919191", new Date(1999, 12, 10), Sex.MALE, BloodType.A, true);
        Member m4 = new Member("Alice Ruby", "0922456876", new Date(2000, 5, 30), Sex.FEMALE, BloodType.AB, false);

        List<Member> members = new ArrayList<>();
        members.add(m1);
        members.add(m2);
        members.add(m3);
        members.add(m4);

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            welcome();
            choice = sc.nextInt();

            InnerMemberFilter filter = null;

            switch (choice) {
                case 1:
                    filter = new VIPFilter();
                    System.out.println("VIP Members:\n");
                    break;
                case 2:
                    filter = new SexFilter(Sex.MALE);
                    System.out.println("Male Members:\n");
                    break;
                case 3:
                    filter = new SexFilter(Sex.FEMALE);
                    System.out.println("Female Members:\n");
                    break;
                case 4:
                    filter = new BloodTypeFilter(BloodType.A);
                    System.out.println("Blood Type A Members:\n");
                    break;
                case 5:
                    filter = new BloodTypeFilter(BloodType.B);
                    System.out.println("Blood Type B Members:\n");
                    break;
                case 6:
                    filter = new BloodTypeFilter(BloodType.AB);
                    System.out.println("Blood Type AB Members:\n");
                    break;
                case 7:
                    filter = new BloodTypeFilter(BloodType.O);
                    System.out.println("Blood Type O Members:\n");
                    break;
                case 8:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
                    continue;
            }

            for (Member m : members) {
                if (filter.accept(m)) {
                    System.out.println(m);
                    System.out.println("");
                }
            }

            do {
                System.out.println("-".repeat(50));
                System.out.println("Do You Want to Continue? (0: Yes, 8: No)");
                choice = sc.nextInt();
                if (choice == 8) {
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                }

                if (choice != 0) {
                    System.out.println("Invalid choice");
                }
            } while (choice != 0);

        } while (choice != 8);
        sc.close();
    }

    static void welcome() {
        System.out.println("*".repeat(50));
        System.out.println("Welcome to the Member Filter System\n");
        System.out.println("Please choose the following options:\n");
        System.out.print("1. VIP Members\n" + "2. Male Members\n" + "3. Female Members\n" + "4. Blood Type A Members\n"
                + "5. Blood Type B Members\n" + "6. Blood Type AB Members\n" + "7. Blood Type O Members\n"
                + "8. Exit\n");
        System.out.println("*".repeat(50));
    }
}