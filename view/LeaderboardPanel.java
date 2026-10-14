package view;

import data.*;
import view.components.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * LeaderboardPanel
 * หน้าจอตารางอันดับคะแนน
 * อันดับ 1-3 ได้สีทอง เงิน ทองแดง และมีปุ่ม Home ไว้กดกลับหน้าหลัก
 */
public class LeaderboardPanel extends  GradientPanel{
    private static final Color GOLD = new Color(0xF2C94C);
    private static final Color SILVER = new Color(0xC3CEDD);
    private static final Color BRONZE = new Color(0xD1A073);

    private final DefaultTableModel model;
    private boolean hasData = false;
    private static final int MAX_ROWS = 10;


public LeaderboardPanel(GameFrame frame) {
        setPreferredSize(new Dimension(GamePanel.PANEL_W, GamePanel.PANEL_H));  // บอกขนาดหน้า ให้เท่ากับหน้าเกม
        setLayout(new BorderLayout(0, 12)); // แบ่งหน้าเป็น บน กลาง ล่าง และเว้นช่องห่างแนวตั้ง 12 px
        setBorder(BorderFactory.createEmptyBorder(50, 20, 24, 20)); // เว้นขอบรอบหน้า

    // ทำป้ายสีเหลืองมุมมน ไว้ใส่ชื่อหัวข้อ
     RoundedPanel titlePill = new RoundedPanel(Theme.YELLOW, 12); 
     titlePill.setLayout(new BorderLayout());      // ทำให้ข้อความขยายเต็มป้ายได้
     JLabel title = new JLabel("Leaderboard",SwingConstants.CENTER);    // ข้อความ Leaderboard จัดให้อยู่ตรงกลาง
     title.setFont(Theme.font(Font.BOLD, 20));
     title.setForeground(Color.WHITE);
     title.setBorder(BorderFactory.createEmptyBorder(6, 16, 6, 16));    // เว้นที่ว่างรอบข้อความ 
     titlePill.add(title);  // เอาข้อความไปแปะบนป้ายสีเหลือง

    // สร้างปุ่ม Home สีม่วง
    RoundedButton homeBtn = new RoundedButton("Home", Theme.PURPLE);    
    homeBtn.setPreferredSize(new Dimension(100, 40));     // ขนาดปุ่ม
    homeBtn.addActionListener(e -> frame.goHome()); // พอกดปุ่ม ให้บอก GameFrame ว่าพากลับหน้า Home หน่อย

    // ทำแถบด้านบน ไว้วางป้ายหัวข้อกับปุ่ม Home คู่กัน
     JPanel header = new JPanel(new BorderLayout(10, 0)); 
        header.setOpaque(false);    // ให้แถบโปร่งใส จะได้เห็นพื้นหลังสีฟ้าข้างหลัง
        header.add(titlePill, BorderLayout.CENTER); //ป้ายหัวข้อไว้ตรงกลาง
        header.add(homeBtn, BorderLayout.EAST); //ปุ่ม home ไว้ขวา
        add(header, BorderLayout.NORTH); // เอาไว้ด้านบน

         model = new DefaultTableModel(      // เริ่มสร้างกล่องเก็บข้อมูลของตาราง
         new Object[][] {},
        new String[] {"No.", "Name", "Score"}    // ชื่อหัวคอลัมน์ 3 อัน
        ) {
          @Override
          //ทำให้ตารางนี้ไว้ดูอย่างเดียว แก้ไม่ได้
         public boolean isCellEditable(int row, int col) {
        return false;
    }
};
        // สร้างตาราง โดยเอาข้อมูลจาก model มาโชว์ 
         JTable table = new JTable(model){
            @Override
            // ถูกเรียกก่อนวาดทุกช่อง เอาไว้ปรับสีพื้นช่อง
            public Component prepareRenderer(javax.swing.table.TableCellRenderer r, int row, int col) { 
                Component c = super.prepareRenderer(r, row, col);   // ให้ตารางเตรียมช่องแบบปกติก่อน เราค่อยมาแก้สีทีหลัง
                Color bg = Color.WHITE; // เริ่มต้นให้พื้นเป็นสีขาวก่อน
                if (hasData) {
                    if (row == 0) bg = GOLD;    // แถวแรก (อันดับ 1) เปลี่ยนเป็นสีทอง
                    else if (row == 1) bg = SILVER; // แถวสอง (อันดับ 2) เป็นสีเงิน
                    else if (row == 2) bg = BRONZE; // แถวสาม (อันดับ 3) เป็นสีทองแดง
                }
                c.setBackground(bg);    // ใส่สีพื้นให้ช่องนี้
                c.setForeground(Theme.TEXT_DARK);    // ให้ตัวหนังสือเป็นสีเข้ม อ่านง่าย
                return c;   // ส่งช่องที่แต่งเสร็จแล้วกลับไปให้ตารางวาด
            }
         };

         table.setFont(Theme.font(Font.PLAIN, 13));   // ตัวอักษรในตาราง
        table.setRowHeight(55); // ให้แต่ละแถวสูง 32 px
        table.setEnabled(false);    // ปิดไม่ให้คลิกเลือกแถวได้ ดูอย่างเดียว
        table.setShowGrid(false);    // ซ่อนเส้นตารางที่คั่นช่อง

         DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);   // ให้ข้อความทุกช่องอยู่ตรงกลางช่อง
        table.setDefaultRenderer(Object.class, center); // ทุกช่องอยู่ตรงกลาง

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer(); //หน้าตาของหัวตาราง (แถว No. / Name / Score)
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // ข้อความหัวตารางอยู่กลางช่อง
        headerRenderer.setBackground(Theme.NAVY); // พื้นหลัง หัวข้อ
        headerRenderer.setForeground(Color.WHITE); // สีตัวอักษร
        headerRenderer.setFont(Theme.font(Font.BOLD, 13)); // ตัวหนา ขนาด
        table.getTableHeader().setDefaultRenderer(headerRenderer); //ให้หัวข้อใน table ทำให้เหนือนกับ headerRenderer
        table.getTableHeader().setReorderingAllowed(false); // ห้ามลากสลับตำแหน่งคอลัมน์เล่น
        table.getTableHeader().setPreferredSize(new Dimension(0, 30));  // กำหนดความสูงของหัว
        table.getColumnModel().getColumn(0).setPreferredWidth(10);  // คอลัมน์แรก (No.) กว้าง 50
        table.getColumnModel().getColumn(1).setPreferredWidth(100); // คอลัมน์สอง (Name) กว้าง 170 เพราะชื่อยาวกว่าเพื่อน
        table.getColumnModel().getColumn(2).setPreferredWidth(80);  // คอลัมน์สาม (Score) กว้าง 80

        //สร้างกล่องสำหรับบรรจุหัวตารางและข้อมูลคะแนน
        JPanel box = new JPanel(new BorderLayout());                
        box.setBorder(BorderFactory.createLineBorder(Theme.PURPLE, 3)); // เพิ่มเส้นขอบ
        box.add(table.getTableHeader(), BorderLayout.NORTH);       // วางหัวตารางไว้ด้านบน 
        box.add(table, BorderLayout.CENTER);     // วางข้อมูลตารางไว้ตรงกลาง

        // สร้างแผงตรงกลางของหน้าจอ
        JPanel middle = new JPanel(new BorderLayout());             
        middle.setOpaque(false);    // ทำให้พื้นหลังของแผงโปร่งใส
        middle.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));        // เพิ่มระยะห่างด้านบน                          
        middle.add(box, BorderLayout.NORTH);   // วางกล่องตารางไว้ด้านบน                    
        add(middle, BorderLayout.CENTER);   // เพิ่มแผงตารางลงในพื้นที่ตรงกลาง
}
 public void refresh() {
    model.setRowCount(0);  // ลบข้อมูลเก่าออกจากตารางทั้งหมดก่อน
        List<ScoreEntry> entries = LeaderboardManager.load();   // โหลดรายการคะแนนจาก LeaderboardManager
        hasData = !entries.isEmpty();    // ตรวจสอบว่ามีข้อมูลคะแนนหรือไม่
        int shown = Math.min(entries.size(), MAX_ROWS);     // คำนวณจำนวนรายการที่จะแสดง โดยแสดงไม่เกิน 10 อันดับ
        for (int i = 0; i < shown; i++) {       // วนลูปนำข้อมูลคะแนนแต่ละรายการมาใส่ในตาราง
        ScoreEntry entry = entries.get(i);  // ดึงข้อมูลคะแนนของผู้เล่นในลำดับปัจจุบัน
        model.addRow(new Object[]{String.valueOf(i + 1), entry.getName(), String.valueOf(entry.getScore())}); // เพิ่มข้อมูลลงในตาราง
        }
        // ถ้าไม่มีข้อมูลคะแนน ให้แสดงข้อความแจ้งเตือนในตาราง
        if (!hasData) {
            model.addRow(new Object[]{"-", "No information available", "-"});
        }
}
}
