package org.bouncycastle.pqc.crypto.sike;

class P434
    extends Internal
{
    // Encoding of field elementsL, elements over Z_orderL, elements over GF(p^2) and elliptic curve points:
    // --------------------------------------------------------------------------------------------------
    // Elements over GF(p) and Z_order are encoded with the least significant octet (and digit) located at the leftmost position (i.e.L, little endian format).
    // Elements (a+b*i) over GF(p^2)L, where a and b are defined over GF(p)L, are encoded as {aL, b}L, with a in the least significant position.
    // Elliptic curve points P = (x,y) are encoded as {xL, y}L, with x in the least significant position.
    // InternallyL, the number of digits used to represent all these elements is obtained by approximating the number of bits to the immediately greater multiple of 32.
    // For exampleL, a 434-bit field element is represented with Ceil(434 / 64) = 7 64-bit digits or Ceil(434 / 32) = 14 32-bit digits.

    //
    // Curve isogeny system "SIDHp434". Base curve: Montgomery curve By^2 = Cx^3 + Ax^2 + Cx defined over GF(p434^2)L, where A=6L, B=1L, C=1 and p434 = 2^216*3^137-1
    //
    P434(boolean isCompressed)
    {
        super();
        this.COMPRESS = isCompressed;
        CRYPTO_SECRETKEYBYTES = 374;
        CRYPTO_PUBLICKEYBYTES = 330;
        CRYPTO_BYTES = 16;
        CRYPTO_CIPHERTEXTBYTES = 346;
        if (isCompressed)
        {
            CRYPTO_SECRETKEYBYTES = 350;
            CRYPTO_PUBLICKEYBYTES = 197;
            CRYPTO_CIPHERTEXTBYTES = 236;
        }


        this.NWORDS_FIELD = 7;               // Number of words of a 434-bit field element
        this.PRIME_ZERO_WORDS = 3;               // Number of "0" digits in the least significant part of p434 + 1

        // Basic constants

        this.NBITS_FIELD = 434;
        this.MAXBITS_FIELD = 448;
        this.MAXWORDS_FIELD = ((MAXBITS_FIELD + RADIX - 1) / RADIX);     // Max. number of words to represent field elements
        this.NWORDS64_FIELD = ((NBITS_FIELD + 63) / 64);               // Number of 64-bit words of a 434-bit field element;
        this.NBITS_ORDER = 256;
        this.NWORDS_ORDER = ((NBITS_ORDER + RADIX - 1) / RADIX);       // Number of words of oA and oB, where oA and oB are the subgroup orders of Alice and Bob, resp.;
        this.NWORDS64_ORDER = ((NBITS_ORDER + 63) / 64);               // Number of 64-bit words of a 224-bit element;
        this.MAXBITS_ORDER = NBITS_ORDER;
        this.ALICE = 0;
        this.BOB = 1;
        this.OALICE_BITS = 216;
        this.OBOB_BITS = 218;
        this.OBOB_EXPON = 137;
        this.MASK_ALICE = 0xFF;
        this.MASK_BOB = 0x01;
        this.PRIME = PRIME;
        this.PARAM_A = 6;
        this.PARAM_C = 1;
        // Fixed parameters for isogeny tree computation
        this.MAX_INT_POINTS_ALICE = 7;
        this.MAX_INT_POINTS_BOB = 8;
        this.MAX_Alice = 108;
        this.MAX_Bob = 137;
        this.MSG_BYTES = 16;
        this.SECRETKEY_A_BYTES = ((OALICE_BITS + 7) / 8);
        this.SECRETKEY_B_BYTES = ((OBOB_BITS - 1 + 7) / 8);
        this.FP2_ENCODED_BYTES = 2 * ((NBITS_FIELD + 7) / 8);

        if (COMPRESS)
        {
            this.MASK2_BOB = 0x00;
            this.MASK3_BOB = 0x7F;
            this.ORDER_A_ENCODED_BYTES = SECRETKEY_A_BYTES;
            this.ORDER_B_ENCODED_BYTES = SECRETKEY_B_BYTES;
            this.PARTIALLY_COMPRESSED_CHUNK_CT = (4 * ORDER_A_ENCODED_BYTES + FP2_ENCODED_BYTES + 2);
            this.COMPRESSED_CHUNK_CT = (3 * ORDER_A_ENCODED_BYTES + FP2_ENCODED_BYTES + 2);
            this.UNCOMPRESSEDPK_BYTES = 330;
            // Table sizes used by the Entangled basis generation
            this.TABLE_R_LEN = 17;
            this.TABLE_V_LEN = 34;
            this.TABLE_V3_LEN = 20;
            // Parameters for discrete log computations
            // Binary Pohlig-Hellman reduced to smaller logs of order ell^W
            this.W_2 = 4;
            this.W_3 = 3;
            // ell^w
            this.ELL2_W = (1 << W_2);
            this.ELL3_W = 27;
            // ell^(e mod w)
            this.ELL2_EMODW = (1 << (OALICE_BITS % W_2));
            this.ELL3_EMODW = 9;
            // # of digits in the discrete log
            this.DLEN_2 = ((OALICE_BITS + W_2 - 1) / W_2); // ceil(eA/W_2)
            this.DLEN_3 = ((OBOB_EXPON + W_3 - 1) / W_3); // ceil(eB/W_3)

            // Use compressed tables: FULL_SIGNED
//            this.ELL2_FULL_SIGNED = // Uses signed digits to reduce table size by half;
//            this.ELL3_FULL_SIGNED = // Uses signed digits to reduce table size by half;

            // Length of the optimal strategy path for Pohlig-Hellman
//            if(W_2 == 4)
//                this.PLEN_2 = 55;
//            if (W_3 == 3)
//                this.PLEN_3 = 47;

        }
        PRIME = new long[]{0xFFFFFFFFFFFFFFFFL, 0xFFFFFFFFFFFFFFFFL, 0xFFFFFFFFFFFFFFFFL,
            0xFDC1767AE2FFFFFFL, 0x7BC65C783158AEA3L, 0x6CFC5FD681C52056L, 0x0002341F27177344L};
        PRIMEx2 = new long[]{0xFFFFFFFFFFFFFFFEL, 0xFFFFFFFFFFFFFFFFL, 0xFFFFFFFFFFFFFFFFL, 0xFB82ECF5C5FFFFFFL,
            0xF78CB8F062B15D47L, 0xD9F8BFAD038A40ACL, 0x0004683E4E2EE688L};
        PRIMEx4 = new long[]{0xFFFFFFFFFFFFFFFCL, 0xFFFFFFFFFFFFFFFFL, 0xFFFFFFFFFFFFFFFFL, 0xF705D9EB8BFFFFFFL,
            0xEF1971E0C562BA8FL, 0xB3F17F5A07148159L, 0x0008D07C9C5DCD11L};
        PRIMEp1 = new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0xFDC1767AE3000000L,
            0x7BC65C783158AEA3L, 0x6CFC5FD681C52056L, 0x0002341F27177344L};
        PRIMEx16p = new long[]{0x0000000000000010L, 0x0000000000000000L, 0x0000000000000000L, 0x47D130A3A0000000L,
            0x873470F9D4EA2B80L, 0x6074052FC75BF530L, 0x54497C1B1D119772L, 0xC55F373D2CDCA412L,
            0x732CA2221C664B96L, 0x6445AB96AF6359A5L, 0x221708AB42ABE1B4L, 0xAE3D3D0063244F01L,
            0x18B920F2ECF68816L, 0x0000004DB194809DL};
        Alice_order = new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000001000000};
        Bob_order = new long[]{0x58AEA3FDC1767AE3L, 0xC520567BC65C7831L, 0x1773446CFC5FD681L, 0x0000000002341F27};
        A_gen = new long[]{0x05ADF455C5C345BFL, 0x91935C5CC767AC2BL, 0xAFE4E879951F0257L, 0x70E792DC89FA27B1L,
            0xF797F526BB48C8CDL, 0x2181DB6131AF621FL, 0x00000A1C08B1ECC4L,    // XPA0
            0x74840EB87CDA7788L, 0x2971AA0ECF9F9D0BL, 0xCB5732BDF41715D5L, 0x8CD8E51F7AACFFAAL,
            0xA7F424730D7E419FL, 0xD671EB919A179E8CL, 0x0000FFA26C5A924AL,    // XPA1
            0xFEC6E64588B7273BL, 0xD2A626D74CBBF1C6L, 0xF8F58F07A78098C7L, 0xE23941F470841B03L,
            0x1B63EDA2045538DDL, 0x735CFEB0FFD49215L, 0x0001C4CB77542876L,    // XQA0
            0xADB0F733C17FFDD6L, 0x6AFFBD037DA0A050L, 0x680EC43DB144E02FL, 0x1E2E5D5FF524E374L,
            0xE2DDA115260E2995L, 0xA6E4B552E2EDE508L, 0x00018ECCDDF4B53EL,    // XQA1
            0x01BA4DB518CD6C7DL, 0x2CB0251FE3CC0611L, 0x259B0C6949A9121BL, 0x60E17AC16D2F82ADL,
            0x3AA41F1CE175D92DL, 0x413FBE6A9B9BC4F3L, 0x00022A81D8D55643L,    // XRA0
            0xB8ADBC70FC82E54AL, 0xEF9CDDB0D5FADDEDL, 0x5820C734C80096A0L, 0x7799994BAA96E0E4L,
            0x044961599E379AF8L, 0xDB2B94FBF09F27E2L, 0x0000B87FC716C0C6L};  // XRA1
        B_gen = new long[]{0x6E5497556EDD48A3L, 0x2A61B501546F1C05L, 0xEB919446D049887DL, 0x5864A4A69D450C4FL,
            0xB883F276A6490D2BL, 0x22CC287022D5F5B9L, 0x0001BED4772E551FL,    // XPB0
            0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L,
            0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L,    // XPB1
            0xFAE2A3F93D8B6B8EL, 0x494871F51700FE1CL, 0xEF1A94228413C27CL, 0x498FF4A4AF60BD62L,
            0xB00AD2A708267E8AL, 0xF4328294E017837FL, 0x000034080181D8AEL,    // XQB0
            0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L,
            0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L,    // XQB1
            0x283B34FAFEFDC8E4L, 0x9208F44977C3E647L, 0x7DEAE962816F4E9AL, 0x68A2BA8AA262EC9DL,
            0x8176F112EA43F45BL, 0x02106D022634F504L, 0x00007E8A50F02E37L,    // XRB0
            0xB378B7C1DA22CCB1L, 0x6D089C99AD1D9230L, 0xEBE15711813E2369L, 0x2B35A68239D48A53L,
            0x445F6FD138407C93L, 0xBEF93B29A3F6B54BL, 0x000173FA910377D3L};  // XRB1
        Montgomery_R2 = new long[]{0x28E55B65DCD69B30L, 0xACEC7367768798C2L, 0xAB27973F8311688DL, 0x175CC6AF8D6C7C0BL,
            0xABCD92BF2DDE347EL, 0x69E16A61C7686D9AL, 0x000025A89BCDD12AL};
        Montgomery_one = new long[]{0x000000000000742CL, 0x0000000000000000L, 0x0000000000000000L, 0xB90FF404FC000000L,
            0xD801A4FB559FACD4L, 0xE93254545F77410CL, 0x0000ECEEA7BD2EDAL};
        strat_Alice = new int[]{48, 28, 16, 8, 4, 2, 1, 1, 2, 1, 1, 4, 2, 1, 1, 2, 1, 1, 8, 4, 2, 1, 1, 2, 1, 1, 4, 2, 1, 1, 2, 1, 1, 13, 7, 4, 2, 1, 1, 2, 1, 1, 3, 2, 1, 1,
            1, 1, 5, 4, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1, 21, 12, 7, 4, 2, 1, 1, 2, 1, 1, 3, 2, 1, 1, 1, 1, 5, 3, 2, 1, 1, 1, 1, 2, 1, 1, 1, 9, 5, 3, 2, 1, 1,
            1, 1, 2, 1, 1, 1, 4, 2, 1, 1, 1, 2, 1, 1};
        strat_Bob = new int[]{66, 33, 17, 9, 5, 3, 2, 1, 1, 1, 1, 2, 1, 1, 1, 4, 2, 1, 1, 1, 2, 1, 1, 8, 4, 2, 1, 1, 1, 2, 1, 1, 4, 2, 1, 1, 2, 1, 1, 16, 8, 4, 2, 1, 1, 1,
            2, 1, 1, 4, 2, 1, 1, 2, 1, 1, 8, 4, 2, 1, 1, 2, 1, 1, 4, 2, 1, 1, 2, 1, 1, 32, 16, 8, 4, 3, 1, 1, 1, 1, 2, 1, 1, 4, 2, 1, 1, 2, 1, 1, 8, 4, 2,
            1, 1, 2, 1, 1, 4, 2, 1, 1, 2, 1, 1, 16, 8, 4, 2, 1, 1, 2, 1, 1, 4, 2, 1, 1, 2, 1, 1, 8, 4, 2, 1, 1, 2, 1, 1, 4, 2, 1, 1, 2, 1, 1};
    }
}