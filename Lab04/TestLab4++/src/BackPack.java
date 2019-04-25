public class BackPack {
    public static void main(String[] args) {
        //鏈�澶у閲忎负10
        int m = 10;
        int n = 3;
        /*
        鏀惧叆鐨勪笁涓墿浣撻噸閲忓垎鍒负3锛� 4锛� 5锛涗环鍊煎垎鍒负4锛� 5锛� 6
         */
        int w[] = {3, 4, 5};
        int p[] = {4, 5, 6};
        int c[][] = BackPack_Solution(m, n, w, p);
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                System.out.print(c[i][j] + ",");
                if (j == m) {
                    System.out.println();
                }
            }
        }
        //printPack(c, w, m, n);

    }

    /**
     * @param m 琛ㄧず鑳屽寘鐨勬渶澶у閲�
     * @param n 琛ㄧず鍟嗗搧涓暟
     * @param w 琛ㄧず鍟嗗搧閲嶉噺鏁扮粍
     * @param p 琛ㄧず鍟嗗搧浠峰�兼暟缁�
     */
    public static int[][] BackPack_Solution(int m, int n, int[] w, int[] p) {
        //c[i][v]琛ㄧず鍓峣浠剁墿鍝佹伆鏀惧叆涓�涓噸閲忎负m鐨勮儗鍖呭彲浠ヨ幏寰楃殑鏈�澶т环鍊�
        //閫掓帹寮忥細c[i][m]=max{c[i-1][m-w[i]]+pi , c[i-1][m]}
        int c[][] = new int[n + 1][m + 1];
        for (int i = 0; i < n + 1; i++) {
            c[i][0] = 0;
        }
        for (int j = 0; j < m + 1; j++) {
            c[0][j] = 0;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                //褰撶墿鍝佷负i浠堕噸閲忎负j鏃讹紝濡傛灉绗琲浠剁殑閲嶉噺(w[i-1])灏忎簬閲嶉噺j鏃讹紝c[i][j]涓轰笅鍒椾袱绉嶆儏鍐典箣涓�锛�
                //(1)鐗╁搧i涓嶆斁鍏ヨ儗鍖呬腑锛屾墍浠[i][j]涓篶[i-1][j]鐨勫��
                //(2)鐗╁搧i鏀惧叆鑳屽寘涓紝鍒欒儗鍖呭墿浣欓噸閲忎负j-w[i-1],鎵�浠[i][j]涓篶[i-1][j-w[i-1]]鐨勫�煎姞涓婂綋鍓嶇墿鍝乮鐨勪环鍊�
                if (w[i - 1] <= j) {
                    if (c[i - 1][j] < (c[i - 1][j - w[i - 1]] + p[i - 1])) {
                        c[i][j] = c[i - 1][j - w[i - 1]] + p[i - 1];
                    } else {
                        c[i][j] = c[i - 1][j];
                    }
                } else {
                    c[i][j] = c[i - 1][j];
                }
            }
        }
        return c;
    }
}