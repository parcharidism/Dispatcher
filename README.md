# Dispatcher
Σχεδίαση Λειτουργικών Συστημάτων - Προαιρετική εργασία

Η εφαρμογή προσομοίωσης του Dispatcher είναι γραμμένη στη γλώσσα προγραμματισμού Java και χρησιμοποιεί τη γραμμή εντολών χωρίς γραφικό περιβάλλον. Πρόκειται για ατομική προσπάθεια και η υλοποίηση έγινε στο Netbeans 19.

Η εφαρμογή συνίσταται από 4 κλάσεις, την κυρίως κλάση (Dispatcher.java), μία βοηθητική κλάση με μεθόδους – εργαλεία (Utils.java) και δύο δομικές κλάσεις (PriorityQueue.java και Process.java). Ξεκινάει χωρίς command line arguments και ο χρήστης ερωτάται αρχικά για τον αριθμό των διεργασιών που θα δημιουργήσει και στη συνέχεια εισάγει τις παραμέτρους τους. Τέλος, ερωτάται για το κβάντο χρόνου (quantum). Στη συνέχεια, η εφαρμογή «τακτοποιεί» χρονικά τις διεργασίες του χρήστη άπαξ και ξεκινάει την επεξεργασία τους σύμφωνα με τον αλγόριθμο ουρών προτεραιοτήτων με την επιπλέον περίπτωση της εκ περιτροπής δρομολόγησης. 

Όταν μία διεργασία ολοκληρώνεται η εφαρμογή τυπώνει σχετικό μήνυμα στην κονσόλα με τον αριθμό της διεργασίας και τους χρόνους απόκρισης και επιστροφής της. Στη συνέχεια, εισάγεται σε μία λίστα και μόλις συμπληρωθούν δέκα ολοκληρωμένες διεργασίες (παραδοχή του προγραμματιστή) τυπώνεται ο μέσος όρος απόκρισης και επιστροφής τους. Μετέπειτα η λίστα αδειάζει και ο χρήστης ερωτάται εάν επιθυμεί να συνεχίσει την προσομοίωση (με γεννήτρια διεργασιών) ή να περατώσει την εκτέλεση.
